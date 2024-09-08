package com.hrp.user.microservices.user.infrastructure.inputports;

import com.hrp.user.microservices.common.exceptions.AlreadyExistsException;
import com.hrp.user.microservices.user.application.createclient.CreateClientRequest;

public interface CreateClientInputPort {
    void createClient(CreateClientRequest createClientRequest) throws AlreadyExistsException, IllegalArgumentException;
}
