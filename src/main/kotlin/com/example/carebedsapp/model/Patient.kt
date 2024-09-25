package com.example.carebedsapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
data class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val name: String,

    val email: String,

    val phoneNumber: String,
    val password: String,
    val Status:String,
    val address:String,
    val problem:String,
    var registeredHospital:Hospital? = null,

    @ManyToOne(mappedBy = "registeredUsers", fetch = FetchType.LAZY)
    @JsonIgnore
    var registeredBed:Bed? = null


)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Patient) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}

