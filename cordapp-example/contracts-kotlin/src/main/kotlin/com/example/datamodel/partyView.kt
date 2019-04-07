package com.example.datamodel

import net.corda.core.serialization.CordaSerializable
import java.io.Serializable
import javax.persistence.*

@CordaSerializable
@Entity
@Table(name = "partyView")
class PartyView : Serializable {
    @Id
    @Column(name = "firstName")
    var firstName: String?

    @Column(name = "lastName")
    var lastName: String?

    @Column(name = "policyNum")
    var policyNum: String?

    @Column(name = "licensePlate")
    var licensePlate: String?

    @Column(name = "eventLocation")
    var eventLocation: String?

    @Column( name = "carrier")
    var carrier: String?

    @Column(name = "description")
    var description: String?

    constructor(firstName: String?, lastName: String?, policyNumber: String?,
                licensePlate: String?, location: String?, carrier: String?, description:String?)
    {
        this.firstName = firstName
        this.lastName = lastName
        this.policyNum = policyNumber
        this.licensePlate = licensePlate
        this.eventLocation = location
        this.carrier = carrier
        this.description = description

    }

    constructor() {
        this.firstName = null
        this.lastName = null
        this.policyNum = null
        this.licensePlate = null
        this.eventLocation = null
        this.carrier = null
        this.description = null
    }
}
