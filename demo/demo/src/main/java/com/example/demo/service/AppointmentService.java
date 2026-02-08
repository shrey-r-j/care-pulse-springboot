package com.example.demo.service;

import com.example.demo.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(Appointment appointment);
    List<Appointment> getAppointmentByPatient(Long patientId);
}
