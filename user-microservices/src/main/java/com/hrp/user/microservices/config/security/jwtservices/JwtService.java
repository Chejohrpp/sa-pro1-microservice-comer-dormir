package com.hrp.user.microservices.config.security.jwtservices;

import com.hrp.user.microservices.user.domain.UserRole;

public interface JwtService {
    String generateToken(String username, UserRole role);

    String getUsername(String token);

    String getPayload(String token);

    boolean isValid(String token);

    void updateTokenExpiration(String username);

    public boolean isTokenExpired(String username);
}
