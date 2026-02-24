package com.example.demo.service;

import com.example.demo.dto.request.PatientRequestDTO;
import com.example.demo.dto.response.PatientResponseDTO;
import com.example.demo.entity.Patient;
import com.example.demo.entity.User;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setBirthDate(dto.getBirthDate());
        patient.setAddress(dto.getAddress());
        patient.setPhone(dto.getPhone());

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(com.example.demo.utilities.Role.PATIENT);

        patient.setIdentificationNumber(dto.getIdentificationNumber());
        patient.setIdentificationType(dto.getIdentificationType());
        patient.setIdentificationDocumentUrl(dto.getIdentificationDocumentUrl());
        patient.setUser(user);

        Patient saved = patientRepository.save(patient);

        return new PatientResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getUser().getEmail(),
                saved.getPhone(),
                saved.getAddress(),
                saved.getGender(),
                saved.getIdentificationNumber(),
                saved.getIdentificationType(),
                saved.getIdentificationDocumentUrl());
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
    }
}

class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}