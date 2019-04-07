package com.example.test.flow

import com.example.datamodel.PartyView
import com.example.flow.CreateFNOLFlow
import com.example.flow.ExampleFlow
import com.example.state.IOUState
import net.corda.core.contracts.TransactionVerificationException
import net.corda.core.identity.Party
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
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CreateFlowTest {
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
    fun `flow Creates FNOL between Helen (carrier A) and Matt (carrier B)`() {

        val flow = CreateFNOLFlow.Initiator(
                firstName, lastName, policyNum, insuredLP, lossEventLocation, helenDescription,
                dateOfLoss, otherFirstName,
                otherLastName, otherLicensePlate, otherPolicy,
                mattDescription, b.info.singleIdentity())
        val future = a.startFlow(flow)
        network.runNetwork()

    }


}
