package com.example.demo.entity;

import com.example.demo.utilities.IdentificationType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String birthDate;
    private String address;
    private String phone;

    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;

    @Column(unique = true)
    private String identificationNumber;

    private String identificationDocumentUrl;
    private String identificationDocumentId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
