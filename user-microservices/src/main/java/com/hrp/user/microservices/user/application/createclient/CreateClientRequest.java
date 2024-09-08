package com.hrp.user.microservices.user.application.createclient;

import com.hrp.user.microservices.client.domain.Client;
import com.hrp.user.microservices.user.application.createuser.CreateUserRequest;
import com.hrp.user.microservices.user.domain.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientRequest {
    private CreateUserRequest createUserRequest;
    private String phoneNumber;
    private String address;

    public Client toDomain(){
        return Client.builder()
                .user(createUserRequest.toDomain())
                .phoneNumber(phoneNumber)
                .address(address)
                .build();
    }
    public Client toDomain(User user){
        return Client.builder()
                .user(user)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();
    }
}
