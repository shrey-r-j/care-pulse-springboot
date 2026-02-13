package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentResponseDTO {
    private Long id;
    private String status;

    private String reason;
    private String note;
    private String cancellationReason;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime appointmentDate;

    private Long patientId;
    private Long doctorId;


}
