package com.example.demo.service;

import com.example.demo.dto.request.AppointmentRequestDTO;
import com.example.demo.dto.response.AppointmentResponseDTO;
import com.example.demo.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDTO bookAppointment(AppointmentRequestDTO dto);
    List<Appointment> getAppointmentByPatient(Long patientId);
}
