package com.hrp.user.microservices.user.infrastructure.outputports;

import com.hrp.user.microservices.client.domain.Client;

import java.util.Optional;

public interface FindClientOutputPort {
    Optional<Client> findClientByUsername(String username);
}
