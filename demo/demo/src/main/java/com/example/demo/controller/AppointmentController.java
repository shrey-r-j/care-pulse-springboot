package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private AppointmentServiceImpl appointmentService;

    @Autowired
    AppointmentController(AppointmentServiceImpl appointmentService){
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment book(@RequestBody Appointment appointment){
        return appointmentService.bookAppointment(appointment);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getByPatient(@PathVariable Long patientId){
        return appointmentService.getAppointmentByPatient(patientId);
    }
}
