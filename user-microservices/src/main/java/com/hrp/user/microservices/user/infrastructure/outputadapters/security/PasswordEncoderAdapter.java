package com.hrp.user.microservices.user.infrastructure.outputadapters.security;

import com.hrp.user.microservices.user.infrastructure.outputports.PasswordEncoderOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderAdapter implements PasswordEncoderOutputPort {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public PasswordEncoderAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
