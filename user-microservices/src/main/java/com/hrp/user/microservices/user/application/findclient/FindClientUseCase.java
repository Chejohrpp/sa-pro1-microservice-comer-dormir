package com.hrp.user.microservices.user.application.findclient;

import com.hrp.user.microservices.client.domain.Client;
import com.hrp.user.microservices.common.annotation.UseCase;
import com.hrp.user.microservices.user.infrastructure.inputports.FindClientInputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.FindClientOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@UseCase
@Transactional
public class FindClientUseCase implements FindClientInputPort {
    private final FindClientOutputPort findClientOutputPort;

    @Autowired
    public FindClientUseCase(FindClientOutputPort findClientOutputPort) {
        this.findClientOutputPort = findClientOutputPort;
    }

    @Override
    public Optional<Client> findClientByUsername(String username) {
        return findClientOutputPort.findClientByUsername(username);
    }
}
