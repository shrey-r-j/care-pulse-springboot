package com.example.demo.dto.response;

import com.example.demo.utilities.IdentificationType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String gender;

    private String identificationNumber;

    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;

    

    private String identificationDocumentUrl;
}
