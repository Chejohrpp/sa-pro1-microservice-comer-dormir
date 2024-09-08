package com.hrp.user.microservices.user.infrastructure.inputadapters.restapi;

import lombok.Value;

@Value
public class UserResponse {
    String message;

    public static UserResponse of(String message) {
        return new UserResponse(message);
    }
}
