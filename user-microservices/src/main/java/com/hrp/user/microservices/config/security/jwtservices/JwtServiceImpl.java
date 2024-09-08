package com.hrp.user.microservices.config.security.jwtservices;

import com.hrp.user.microservices.user.domain.UserRole;
import com.hrp.user.microservices.user.infrastructure.outputadapters.db.JpaUserEntityRepository;
import com.hrp.user.microservices.user.infrastructure.outputadapters.db.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtServiceImpl implements JwtService {

    public static final String SECRET_KEY ="bmpJGkpYz0Af4ub65tzlnPRX2De1o02uuStUt2y1nhgAXzhngZJtWOgVAlOWYD41";

    private final JpaUserEntityRepository userRepository;

    @Autowired
    public JwtServiceImpl(JpaUserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public String generateToken(String username, UserRole role) {
        return Jwts.builder()
                .claims(Collections.singletonMap("role", role))
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 1800000))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public String getUsername(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    @Override
    public String getPayload(String token) {
        Claims claims = extractClaims(token);
        return claims.toString();
    }

    @Override
    public boolean isValid(String token) {
        Claims claims = extractClaims(token);
        Date expirationDate = claims.getExpiration();
        return new Date().before(expirationDate);
    }

    private Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public void updateTokenExpiration(String username) {
        Optional<UserEntity> userOpt = userRepository.findById(username);
        // TODO validation
        UserEntity user = userOpt.get();
        user.setTokenExpiration(LocalDateTime.now().plusMinutes(15));
        userRepository.save(user);

    }

    @Override
    public boolean isTokenExpired(String username) {
        Optional<UserEntity> userOpt = userRepository.findById(username);
        // TODO validation
        UserEntity user = userOpt.get();
        return user.getTokenExpiration() == null
                || LocalDateTime.now().isAfter(user.getTokenExpiration());
    }
}