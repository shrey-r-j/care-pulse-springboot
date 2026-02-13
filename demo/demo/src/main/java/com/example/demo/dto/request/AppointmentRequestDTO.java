package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentRequestDTO {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;
    private String reason;
    private String note;
}
