package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentRequestDTO {

    @NotNull(message = "Patient Id required")
    private Long patientId;

    @NotNull(message = "Doctor Id required")
    private Long doctorId;

    @NotNull(message = "Appointment date is required")
    private LocalDateTime appointmentDate;

    @NotBlank(message = "Reason is required")
    private String reason;

    @Size(max = 500, message = "Note too long")
    private String note;
}
