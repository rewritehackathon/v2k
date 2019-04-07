package com.example.test.flow

import com.example.flow.CreateFNOLFlow
import com.example.flow.ExampleFlow
import com.example.flow.UpdateFNOLFlow
import com.example.state.FNOLState
import net.corda.core.node.services.queryBy
import net.corda.core.utilities.getOrThrow
import net.corda.testing.core.singleIdentity
import net.corda.testing.node.MockNetwork
import net.corda.testing.node.MockNetworkParameters
import net.corda.testing.node.StartedMockNode
import net.corda.testing.node.TestCordapp
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class UpdateFlowTest {
    private lateinit var network: MockNetwork
    private lateinit var a: StartedMockNode
    private lateinit var b: StartedMockNode

    /* Test Data */
    private var firstName = "Helen"
    private var lastName = "Bread"
    private var policyNum = "policyNum123"
    private var insuredLP = "NYCCAR123"
    private var lossEventLocation = "728 Brock St. New York, NY 10001"
    private var dateOfLoss = LocalDate.now()
    private var otherFirstName = "Matt"
    private var otherLastName = "White"
    private var otherLicensePlate = "MD123455"
    private var otherPolicy = "policyNum321"
    private var helenDescription = "I bumped into Matt"
    private var mattDescription = "Helen bumped into me"
    private var helenCarrier = "MutualLiberty"
    private var mattCarrier = "FarmState"

    private var updatedName = "John"

    @Before
    fun setup() {
        network = MockNetwork(MockNetworkParameters(cordappsForAllNodes = listOf(
                TestCordapp.findCordapp("com.example.contract"),
                TestCordapp.findCordapp("com.example.flow")
        )))
        a = network.createPartyNode()
        b = network.createPartyNode()
        // For real nodes this happens automatically, but we have to manually register the flow for tests.
        listOf(a, b).forEach { it.registerInitiatedFlow(ExampleFlow.Acceptor::class.java) }
        network.runNetwork()
    }

    @After
    fun tearDown() {
        network.stopNodes()
    }

    @Test
    fun `flow Updates FNOL between Helen (carrier A) and Matt (carrier B)`() {

        val aCreateFlow = CreateFNOLFlow.Initiator(
                firstName, lastName, policyNum, insuredLP, lossEventLocation, helenDescription,
                dateOfLoss, otherFirstName,
                otherLastName, otherLicensePlate, otherPolicy,
                null, b.info.singleIdentity())

        val future = a.startFlow(aCreateFlow)
        network.runNetwork()

        val stx = future.getOrThrow()
        stx.verifyRequiredSignatures()

        /* A fnol linear id */
        var createAState = a.services.vaultService.queryBy<FNOLState>().states.single().state.data
        var aCreatedUUID = createAState.linearId


        val updateflow = UpdateFNOLFlow.Initiator( firstName, lastName, policyNum,
                insuredLP, lossEventLocation, helenDescription, dateOfLoss, otherFirstName,
                otherLastName, otherLicensePlate, otherPolicy, mattDescription, a.info.singleIdentity(), aCreatedUUID.id )


        val bUpdateFlow = b.startFlow(updateflow)
        network.runNetwork()
        val bstx = bUpdateFlow.getOrThrow()
        bstx.verifyRequiredSignatures()

        //get the state from the vault of node A
        a.transaction {
            var stateUpdatedForA = a.services.vaultService.queryBy<FNOLState>().states.single().state.data
            assert(stateUpdatedForA.otherLossEventDescription == mattDescription)
        }

        b.transaction {
            var stateUpdatedByB = b.services.vaultService.queryBy<FNOLState>().states[0].state.data
            assert(stateUpdatedByB.otherLossEventDescription == mattDescription)
        }

    }


}
