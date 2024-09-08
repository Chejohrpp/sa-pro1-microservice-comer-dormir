package com.hrp.user.microservices.client.infrastructure.outputadapters.db;

import com.hrp.user.microservices.client.domain.Client;
import com.hrp.user.microservices.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    private String username;

    private String phoneNumber;

    private String address;

    public static ClientEntity from(Client client) {
        return new ClientEntity(client.getUser().getUsername(), client.getPhoneNumber(), client.getAddress());
    }

    public Client toDomain(User user) {
        return Client.builder()
                .user(user)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();
    }
}
