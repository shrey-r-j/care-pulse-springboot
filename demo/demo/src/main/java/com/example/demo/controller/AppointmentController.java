package com.example.demo.controller;

import com.example.demo.dto.request.AppointmentRequestDTO;
import com.example.demo.dto.response.AppointmentResponseDTO;
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
    public AppointmentResponseDTO book(@RequestBody AppointmentRequestDTO dto){
        return appointmentService.bookAppointment(dto);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getByPatient(@PathVariable Long patientId){
        return appointmentService.getAppointmentByPatient(patientId);
    }
}
