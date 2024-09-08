package com.hrp.restaurant.microservice.restaurant.infrastructure.inputports;

import com.hrp.restaurant.microservice.common.exceptions.AlreadyExistsException;
import com.hrp.restaurant.microservice.restaurant.application.createdish.CreateDishRequest;

public interface CreateDishInputPort {
    void createDish(CreateDishRequest createDishRequest) throws AlreadyExistsException;
}
