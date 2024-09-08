package com.hrp.user.microservices.user.infrastructure.outputports;

public interface PasswordEncoderOutputPort {
    String encode(String password);
}
