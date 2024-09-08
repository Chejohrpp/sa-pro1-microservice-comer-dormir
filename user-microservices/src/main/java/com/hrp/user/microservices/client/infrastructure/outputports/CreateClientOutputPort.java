package com.hrp.user.microservices.client.infrastructure.outputports;

import com.hrp.user.microservices.client.domain.Client;

public interface CreateClientOutputPort {
    Client save(Client client);
}
