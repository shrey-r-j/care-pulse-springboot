package com.example.demo.entity;

import com.example.demo.utilities.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //one patient can make many appointments
    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(nullable = false)
    private String reason;

    @Column(length = 1000)
    private String note;

    private String cancellationReason;

    private LocalDateTime appointmentDate;
}
