package com.example.demo.utilities;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Test1 {

    private final PasswordEncoder encoder;

    public Test1(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void fixPassword() {
        System.out.println("Encoded is ");
        System.out.println(encoder.encode("1234"));
    }
}
