package com.example.demo.controller;

import com.example.demo.dto.request.PatientRequestDTO;
import com.example.demo.dto.response.PatientResponseDTO;
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
    public PatientResponseDTO createPatient(@RequestBody PatientRequestDTO patient) {
        return patientService.createPatient(patient);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN') or hasRole('PATIENT')")
    public PatientResponseDTO getPatient(@PathVariable Long id) {
        return mapToResponse(patientService.getPatientById(id));
    }

    private PatientResponseDTO mapToResponse(Patient saved) {
        return new PatientResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getUser().getEmail(),
                saved.getPhone(),
                saved.getAddress(),
                saved.getGender());
    }
}
