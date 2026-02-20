package com.example.demo.dto.response;

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
}
