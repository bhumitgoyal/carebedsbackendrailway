package com.example.carebedsapp.controller

import com.example.carebedsapp.model.Bed
import com.example.carebedsapp.model.Hospital
import com.example.carebedsapp.model.Patient
import com.example.carebedsapp.service.BedService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/beds")
class BedController(private val bedService: BedService) {

    @PostMapping("/register")
    fun registerBed(@RequestBody bed: Bed): ResponseEntity<Bed> {
        val createdBed = bedService.addBed(bed)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBed)
    }

    @DeleteMapping("/{id}")
    fun deleteBed(@PathVariable id: Int): ResponseEntity<String> {
        bedService.deleteBed(id)
        return ResponseEntity.ok("Bed deleted successfully")
    }

    @GetMapping("/{id}")
    fun getBedById(@PathVariable id: Int): ResponseEntity<Bed> {
        val bed = bedService.getBedById(id)
        return ResponseEntity.ok(bed)
    }

    @GetMapping
    fun getAllBeds(): ResponseEntity<List<Bed>> {
        return ResponseEntity.ok(bedService.getAllBeds())
    }

    @GetMapping("/{id}/patient")
    fun getPatient(@PathVariable id: Int): ResponseEntity<Patient?> {
        val patient = bedService.getPatient(id)
        return if (patient != null) {
            ResponseEntity.ok(patient)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("{bedId}/admit/{patientId}")
    fun admitPatient(@PathVariable bedId: Int, @PathVariable patientId: Int): ResponseEntity<String> {
        bedService.admitPatient(patientId, bedId)
        return ResponseEntity.ok("Patient admitted successfully")
    }

    @PostMapping("{bedId}/free/{patientId}")
    fun freePatient(@PathVariable bedId: Int, @PathVariable patientId: Int): ResponseEntity<String> {
        bedService.freePatient(patientId)
        return ResponseEntity.ok("Patient freed from bed successfully")
    }
}
