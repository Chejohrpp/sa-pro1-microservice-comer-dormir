package com.hrp.user.microservices.user.infrastructure.outputports;

import com.hrp.user.microservices.user.domain.User;

import java.util.Optional;

public interface FindUserOutputPort {
    Optional<User> findByUsername(String username);
}
