package com.hrp.user.microservices.user.infrastructure.outputports;

import com.hrp.user.microservices.user.domain.User;

public interface CreateUserOutputPort {
    User save(User user);

}
