package com.hrp.user.microservices.user.infrastructure.inputports;

import com.hrp.user.microservices.common.exceptions.AlreadyExistsException;
import com.hrp.user.microservices.user.application.createuser.CreateUserRequest;
import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.domain.UserRole;

public interface CreateUserInputPort {

    User createUser(CreateUserRequest createUserRequest, UserRole role) throws AlreadyExistsException, IllegalArgumentException;
}
