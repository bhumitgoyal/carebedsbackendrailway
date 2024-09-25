package com.example.carebedsapp.config

import com.example.carebedsapp.model.Bed
import com.example.carebedsapp.model.Hospital
import com.example.carebedsapp.model.Patient
import com.example.carebedsapp.repository.BedRepository
import com.example.carebedsapp.repository.HospitalRepository
import com.example.carebedsapp.repository.PatientRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val hospitalRepository: HospitalRepository,
    private val patientRepository: PatientRepository,
    private val bedRepository: BedRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        // Create and save hospitals
        val hospital1 = Hospital(
            name = "City Hospital",
            address = "123 Main St, Citytown",
            email = "contact@cityhospital.com",
            phoneNumber = "1234567890",
            password = "password1",
            role = "ROLE_HOSPITAL",
            location = "Vellore"
        )

        hospitalRepository.save(hospital1)

        // Create and save beds
        val bed1 = Bed(
            name = "Bed A1",
            availability = "Occupied",
            hospital = hospital1  // Linked to hospital1
        )

        // Add additional beds if necessary
        // val bed2 = Bed(name = "Bed A2", availability = "Available", hospital = hospital1)

        bedRepository.saveAll(listOf(bed1))

        // Create and save patients
        val patient1 = Patient(
            name = "John Doe",
            priority = "High",
            address = "789 Elm St, Citytown",
            registeredHospital = hospital1,  // Reference to hospital1
            registeredBed = bed1,            // Reference to bed1
            role = "ROLE_PATIENT",
            email = "johndoe@example.com",
            phoneNumber = "1112223333",
            password = "password3",
            age = 31,
            location = "789 Elm St, Citytown",
            gender = "Male",
            bloodType = "O+",
            medicalCondition = "Hypertension",
            admissionType = "Emergency",
            medications = "Amlodipine",
            testResults = "Blood Pressure: 150/90"
        )

        patientRepository.save(patient1)

        // Now link bed1 with patient1
        bed1.patient = patient1
        bedRepository.save(bed1)

        // Optional: You can save more patients and beds similarly
        // Example for bed2 and patient2
        // val patient2 = Patient(
        //     name = "Jane Smith",
        //     priority = "Low",
        //     address = "321 Maple St, Citytown",
        //     registeredHospital = hospital1,
        //     registeredBed = bed2,
        //     role = "ROLE_PATIENT",
        //     email = "janesmith@example.com",
        //     phoneNumber = "4445556666",
        //     password = "password4",
        //     age = 29,
        //     location = "321 Maple St, Citytown",
        //     gender = "Female",
        //     bloodType = "A+",
        //     medicalCondition = "Routine checkup"
        // )
        // patientRepository.save(patient2)
        // bed2.patient = patient2
        // bedRepository.save(bed2)
    }
}
