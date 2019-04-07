package com.example.schema

import net.corda.core.schemas.MappedSchema
import net.corda.core.schemas.PersistentState
import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * The family of schemas for FNOLState
 */
object FNOLSchema

/**
 * An FNOL schema.
 */
object FNOLSchemaV1 : MappedSchema(
        schemaFamily = FNOLSchema.javaClass,
        version = 1,
        mappedTypes = listOf(FNOL::class.java)) {
    @Entity
    @Table(name = "iou_states")
    class FNOL(
            @Column(name = "carrierName")
            var carrierName: String,

            @Column(name = "otherCarrier")
            var otherCarrier: String,

            @Column(name = "firstName")
            var firstName: String,

            @Column(name = "lastName")
            var lastName: String,

            @Column(name = "insuredPolicy")
            var insuredPolicy: String,

            @Column(name = "insuredLicensePlate")
            var insuredLicensePlate: String,

            @Column(name = "eventLocation")
            var eventLocation: String,

            @Column(name = "firstDescription")
            var firstDescription: String?,

            @Column(name = "otherFirstName")
            var otherFirstName: String?,

            @Column(name = "otherLastName")
            var otherLastName: String?,

            @Column(name = "otherLicensePlate")
            var otherLicensePlate: String?,

            @Column(name = "otherPolicy")
            var otherPolicy: String,

            @Column(name = "otherDescription")
            var otherDescription: String?,

            @Column(name = "dateOfLoss")
            var dateOfLoss: LocalDate,

            @Column(name = "linear_id")
            var linearId: UUID

    ) : PersistentState() {
        // Default constructor required by hibernate.
        constructor(): this("", "", "", "", "",
                "", "","","", "",
                "","", null,
                LocalDate.now(), UUID.randomUUID())
    }
}