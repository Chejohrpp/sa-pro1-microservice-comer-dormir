package com.hrp.order.microservice.order.infrastructure.outputports.restapi;

public interface CheckUsernameExistsOutputPort {
    boolean checkUserExists(String username);
}
