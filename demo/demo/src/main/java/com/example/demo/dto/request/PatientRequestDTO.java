package com.example.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotBlank(message = "Gender is required")
    private String gender;

    private LocalDateTime birthDate;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone is required")
    private String phone;
}
