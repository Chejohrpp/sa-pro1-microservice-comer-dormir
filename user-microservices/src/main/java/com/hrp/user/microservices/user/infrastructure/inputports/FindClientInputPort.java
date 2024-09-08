package com.hrp.user.microservices.user.infrastructure.inputports;

import com.hrp.user.microservices.client.domain.Client;

import java.util.Optional;

public interface FindClientInputPort {
    Optional<Client> findClientByUsername(String username);
}
