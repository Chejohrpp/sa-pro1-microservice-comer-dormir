package com.hrp.user.microservices.user.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String username; //will be the id
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;
}
