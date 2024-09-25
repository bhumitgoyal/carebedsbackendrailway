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
    val priority: String,
    val address: String,
    val problem: String,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    var registeredHospital: Hospital? = null,

    @JsonIgnore
    @OneToOne(mappedBy = "patient")
    var registeredBed: Bed? = null,

    var role: String,
    val email: String,
    val phoneNumber: String,
    val password: String
) {
    // Initialize the derived properties similarly
    val bedNum: Int? get() = registeredBed?.id
    val hospitalNum: Int? get() = registeredHospital?.id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Patient) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}
