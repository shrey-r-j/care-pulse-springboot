package com.example.demo.service;

import com.example.demo.dto.request.AppointmentRequestDTO;
import com.example.demo.dto.response.AppointmentResponseDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.utilities.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

//    @Autowired
//    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
//        this.appointmentRepository = appointmentRepository;
//        this.patientRepository = patientRepository;
//        this.doctorRepository = doctorRepository;
//    }

    @Override
    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(()-> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(()->new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setReason(dto.getReason());
        appointment.setNote(dto.getNote());
        appointment.setStatus(AppointmentStatus.PENDING);
        Appointment saved = appointmentRepository.save(appointment);

        return new AppointmentResponseDTO(
                saved.getId(),
                saved.getReason(),
                saved.getStatus().name(),
                saved.getCancellationReason(),
                saved.getNote(),
                saved.getAppointmentDate(),
                doctor.getId(),
                patient.getId()
                );

    }

    @Override
    public List<Appointment> getAppointmentByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
}
