package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private PatientServiceImpl patientService;

    @Autowired
    PatientController(PatientServiceImpl patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN') or hasRole('PATIENT')")
    public Patient getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }
}
