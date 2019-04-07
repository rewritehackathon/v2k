package com.example.state

import com.example.contract.FNOLContract
import com.example.schema.FNOLSchemaV1
import net.corda.core.contracts.BelongsToContract
import net.corda.core.contracts.LinearState
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.identity.AbstractParty
import net.corda.core.identity.Party
import net.corda.core.schemas.MappedSchema
import net.corda.core.schemas.PersistentState
import net.corda.core.schemas.QueryableState
import java.time.LocalDate

/**
 * The state object records a first notice of loss shared by more than one party on the ledger
 */
@BelongsToContract(FNOLContract::class)
data class FNOLState(val firstName: String,
                     val lastName: String,
                     val insuredPolicy: String,
                     val insuredLicensePlate: String,
                     val eventLocation: String,
                     val firstLossEventDescription: String?,
                     val dateOfLoss: LocalDate,
                     val otherFirstName: String?,
                     val otherLastName: String?,
                     val otherLicensePlate: String?,
                     val otherPolicy: String,
                     val otherLossEventDescription: String?,
                     val me: Party,
                     val otherCarrier: Party,
                     override val linearId: UniqueIdentifier = UniqueIdentifier()):
        LinearState, QueryableState {
    /** The public keys of the involved parties. */
    override val participants: List<AbstractParty> get() = listOf(me, otherCarrier)

    override fun generateMappedObject(schema: MappedSchema): PersistentState {
        return when (schema) {
            is FNOLSchemaV1 -> FNOLSchemaV1.FNOL(
                    this.me.name.toString(),
                    this.otherCarrier.name.toString(),
                    this.firstName,
                    this.lastName,
                    this.insuredPolicy,
                    this.insuredLicensePlate,
                    this.eventLocation,
                    this.firstLossEventDescription,
                    this.otherFirstName,
                    this.otherLastName,
                    this.otherLicensePlate,
                    this.otherPolicy,
                    this.otherLossEventDescription,
                    this.dateOfLoss,
                    this.linearId.id
            )
            else -> throw IllegalArgumentException("Unrecognised schema $schema")
        }
    }

    override fun supportedSchemas(): Iterable<MappedSchema> = listOf(FNOLSchemaV1)
}

