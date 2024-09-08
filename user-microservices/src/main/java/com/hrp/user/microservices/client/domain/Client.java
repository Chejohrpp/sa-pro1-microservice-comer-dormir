package com.hrp.user.microservices.client.domain;

import com.hrp.user.microservices.user.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private User user;
    private String phoneNumber;
    private String address;
}
