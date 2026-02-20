package com.example.demo.service;

import com.example.demo.entity.Patient;

import com.example.demo.dto.request.PatientRequestDTO;
import com.example.demo.dto.response.PatientResponseDTO;

public interface PatientService {
    PatientResponseDTO createPatient(PatientRequestDTO patient);

    Patient getPatientById(Long id);
}
