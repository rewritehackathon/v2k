package com.example.flow

import co.paralleluniverse.fibers.Suspendable
import com.example.contract.FNOLContract
import com.example.datamodel.PartyView
import com.example.state.FNOLState
import net.corda.core.contracts.Command
import net.corda.core.contracts.requireThat
import net.corda.core.flows.*
import net.corda.core.identity.Party
import net.corda.core.transactions.SignedTransaction
import net.corda.core.transactions.TransactionBuilder
import net.corda.core.utilities.ProgressTracker
import net.corda.core.utilities.ProgressTracker.Step
import java.time.LocalDate

object CreateFNOLFlow {
    @InitiatingFlow
    @StartableByRPC
    class Initiator(val firstName: String,
            val lastName: String,
            val policyNum: String,
            val insuredLP: String,
            val lossEventLocation: String,
            val firstLossEventDescription: String?,
            val dateOfLoss: LocalDate,
            val otherFirstName: String,
            val otherLastName: String,
            val otherLicensePlate: String,
            val otherPolicy: String,
            val otherLossEventDescription: String?,
            val otherCarrier: Party
            ) : FlowLogic<SignedTransaction>() {
        /**
         * The progress tracker checkpoints each stage of the flow and outputs the specified messages when each
         * checkpoint is reached in the code. See the 'progressTracker.currentStep' expressions within the call() function.
         */
        companion object {
            object GENERATING_TRANSACTION : Step("Generating transaction based on new FNOL.")
            object VERIFYING_TRANSACTION : Step("Verifying contract constraints.")
            object SIGNING_TRANSACTION : Step("Signing transaction with our private key.")
            object GATHERING_SIGS : Step("Gathering the counterparty's signature.") {
                override fun childProgressTracker() = CollectSignaturesFlow.tracker()
            }

            object FINALISING_TRANSACTION : Step("Obtaining notary signature and recording transaction.") {
                override fun childProgressTracker() = FinalityFlow.tracker()
            }

            fun tracker() = ProgressTracker(
                    GENERATING_TRANSACTION,
                    VERIFYING_TRANSACTION,
                    SIGNING_TRANSACTION,
                    GATHERING_SIGS,
                    FINALISING_TRANSACTION
            )
        }

        override val progressTracker = tracker()

        /**
         * The flow logic is encapsulated within the call() method.
         */
        @Suspendable
        override fun call(): SignedTransaction {
            // Obtain a reference to the notary we want to use.
            val notary = serviceHub.networkMapCache.notaryIdentities[0]

            // Stage 1.
            progressTracker.currentStep = GENERATING_TRANSACTION
            // Generate an unsigned transaction.
            val me = serviceHub.myInfo.legalIdentities.first()

            val fnolState = FNOLState(firstName, lastName, policyNum, insuredLP, lossEventLocation,
                    firstLossEventDescription, dateOfLoss, otherFirstName,
                    otherLastName, otherLicensePlate, otherPolicy, otherLossEventDescription, me, otherCarrier)

            val txCommand = Command(FNOLContract.Commands.Create(),
                                                                    fnolState.participants.map { it.owningKey })
            val txBuilder = TransactionBuilder(notary)
                    .addOutputState(fnolState, FNOLContract.ID)
                    .addCommand(txCommand)

            // Stage 2. Verify that the transaction is valid.
            progressTracker.currentStep = VERIFYING_TRANSACTION
            txBuilder.verify(serviceHub)

            // Stage 3. Sign transaction
            progressTracker.currentStep = SIGNING_TRANSACTION
            val partSignedTx = serviceHub.signInitialTransaction(txBuilder)

            // Stage 4. Send the state to the counterparty, and receive it back with their signature.
            progressTracker.currentStep = GATHERING_SIGS


            val otherPartySession = initiateFlow(otherCarrier)
            val fullySignedTx = subFlow(CollectSignaturesFlow(partSignedTx, setOf(otherPartySession), GATHERING_SIGS.childProgressTracker()))

            // Stage 5.
            progressTracker.currentStep = FINALISING_TRANSACTION
            // Notarise and record the transaction in both parties' vaults.
            return subFlow(FinalityFlow(fullySignedTx, setOf(otherPartySession), FINALISING_TRANSACTION.childProgressTracker()))
        }
    }

    @InitiatedBy(Initiator::class)
    class Acceptor(val otherPartySession: FlowSession) : FlowLogic<SignedTransaction>() {
        @Suspendable
        override fun call(): SignedTransaction {
            val signTransactionFlow = object : SignTransactionFlow(otherPartySession) {
                override fun checkTransaction(stx: SignedTransaction) = requireThat {
                    val output = stx.tx.outputs.single().data
                    "This must be an FNOL transaction." using (output is FNOLState)
                }
            }
            val txId = subFlow(signTransactionFlow).id

            return subFlow(ReceiveFinalityFlow(otherPartySession, expectedTxId = txId))
        }
    }
}
