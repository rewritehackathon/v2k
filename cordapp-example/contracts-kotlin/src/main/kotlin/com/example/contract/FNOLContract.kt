package com.example.contract

import com.example.state.FNOLState
import com.example.state.IOUState
import net.corda.core.contracts.CommandData
import net.corda.core.contracts.Contract
import net.corda.core.contracts.requireSingleCommand
import net.corda.core.contracts.requireThat
import net.corda.core.transactions.LedgerTransaction

class FNOLContract : Contract {
    companion object {
        @JvmStatic
        val ID = "com.example.contract.FNOLContract"
    }

    /**
     * The verify() function of all the states' contracts must not throw an exception for a transaction to be
     * considered valid.
     */
    override fun verify(tx: LedgerTransaction) {
        val command = tx.commands.requireSingleCommand<Commands>()
        //Potentially check for similar FNOLStates already in ledger
        when (command.value) {
            is Commands.Create -> requireThat {
                "No inputs should be consumed when creating initial report of FNOL." using (tx.inputs.isEmpty())
                "Only one output fnol state should be created." using (tx.outputs.size == 1)
                val out = tx.outputsOfType<FNOLState>().single()
                "All of the participants must be signers." using (command.signers.containsAll(out.participants.map { it.owningKey }))

            }
            is Commands.Update -> requireThat{
                "There must be one consumed state when updating a FNOL." using (tx.inputs.size == 1)
                "Only one output fnol state should be created." using (tx.outputs.size == 1)
            }

        }

    }

    /**
     * This contract only implements one command, Create.
     */
    interface Commands : CommandData {
        class Create : Commands
        class Update : Commands
    }
}
