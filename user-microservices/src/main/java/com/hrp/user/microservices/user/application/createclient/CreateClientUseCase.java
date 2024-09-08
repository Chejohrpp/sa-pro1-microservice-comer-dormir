package com.hrp.user.microservices.user.application.createclient;

import com.hrp.user.microservices.client.domain.Client;
import com.hrp.user.microservices.client.infrastructure.outputports.CreateClientOutputPort;
import com.hrp.user.microservices.common.annotation.UseCase;
import com.hrp.user.microservices.common.exceptions.AlreadyExistsException;
import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.domain.UserRole;
import com.hrp.user.microservices.user.infrastructure.inputports.CreateClientInputPort;
import com.hrp.user.microservices.user.infrastructure.inputports.CreateUserInputPort;
import jakarta.transaction.Transactional;

@UseCase
@Transactional
public class CreateClientUseCase implements CreateClientInputPort {
    private final CreateClientOutputPort createClientOutputPort;
    private final CreateUserInputPort createUserInputPort;

    public CreateClientUseCase(CreateClientOutputPort createClientOutputPort, CreateUserInputPort createUserInputPort) {
        this.createClientOutputPort = createClientOutputPort;
        this.createUserInputPort = createUserInputPort;
    }

    @Override
    public void createClient(CreateClientRequest createClientRequest) throws AlreadyExistsException, IllegalArgumentException {
        //save as user
        User user = createUserInputPort.createUser(createClientRequest.getCreateUserRequest(), UserRole.CLIENT);

        //convert to domain
        Client client =  createClientRequest.toDomain(user);

        //save client
        createClientOutputPort.save(client);
    }
}
