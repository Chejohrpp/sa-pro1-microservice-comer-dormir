package com.hrp.user.microservices.client.infrastructure.outputadapters.db;

import com.hrp.user.microservices.client.domain.Client;
import com.hrp.user.microservices.client.infrastructure.outputports.CreateClientOutputPort;
import com.hrp.user.microservices.common.annotation.PersistenceAdapter;
import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.infrastructure.outputports.FindClientOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@PersistenceAdapter
public class MySqlClientEntityOutputAdapter implements CreateClientOutputPort, FindClientOutputPort {
    private final JpaClientEntityRepository jpaClientEntityRepository;

    @Autowired
    public MySqlClientEntityOutputAdapter(JpaClientEntityRepository jpaClientEntityRepository) {
        this.jpaClientEntityRepository = jpaClientEntityRepository;
    }


    @Override
    public Client save(Client client) {
        ClientEntity clientEntity = ClientEntity.from(client);
        return jpaClientEntityRepository.save(clientEntity)
                .toDomain(client.getUser());
    }

    @Override
    public Optional<Client> findClientByUsername(String username) {
        return jpaClientEntityRepository.findById(username)
                .map(clientEntity -> clientEntity.toDomain(User.builder().username(clientEntity.getUsername()).build()));
    }
}
