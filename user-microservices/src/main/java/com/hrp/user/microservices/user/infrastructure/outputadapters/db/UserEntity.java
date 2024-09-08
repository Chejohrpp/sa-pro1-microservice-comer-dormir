package com.hrp.user.microservices.user.infrastructure.outputadapters.db;

import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.domain.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String username; // will be the id

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

    public UserEntity(String username, String password, String email, String firstName, String lastName, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public static UserEntity from(User user) {
        return new UserEntity(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole()
        );
    }

    public User toDomain(){
        return User.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .email(email)
                .lastName(lastName)
                .role(role)
                .build();
    }

}
