package com.example.demo.service;

import com.example.demo.entity.Patient;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient getPatientById(Long id);
}
