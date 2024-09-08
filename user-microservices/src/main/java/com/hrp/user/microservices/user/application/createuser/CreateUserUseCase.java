package com.hrp.user.microservices.user.application.createuser;

import com.hrp.user.microservices.common.annotation.UseCase;
import com.hrp.user.microservices.common.exceptions.AlreadyExistsException;
import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.domain.UserRole;
import com.hrp.user.microservices.user.infrastructure.inputports.CreateUserInputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.CreateUserOutputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.FindUserOutputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.PasswordEncoderOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@Transactional
public class CreateUserUseCase implements CreateUserInputPort {
    private final CreateUserOutputPort createUserOutputPort;
    private final FindUserOutputPort findUserOutputPort;
    private final PasswordEncoderOutputPort passwordEncoderOutputPort;

    @Autowired
    public CreateUserUseCase(CreateUserOutputPort createUserOutputPort, FindUserOutputPort findUserOutputPort, PasswordEncoderOutputPort passwordEncoderOutputPort) {
        this.createUserOutputPort = createUserOutputPort;
        this.findUserOutputPort = findUserOutputPort;
        this.passwordEncoderOutputPort = passwordEncoderOutputPort;
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest, UserRole role) throws AlreadyExistsException, IllegalArgumentException {
        if (findUserOutputPort.findByUsername(createUserRequest.getUsername()).isPresent()) {
            throw new AlreadyExistsException("Username already exists");
        }
        User user = createUserRequest.toDomain();
        user.setRole(role);
        //set passwd
        user.setPassword(passwordEncoderOutputPort.encode(createUserRequest.getPassword()));
        //save user
        return createUserOutputPort.save(user);
    }
}
