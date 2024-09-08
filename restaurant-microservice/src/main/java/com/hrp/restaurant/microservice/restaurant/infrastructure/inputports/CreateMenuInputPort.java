package com.hrp.restaurant.microservice.restaurant.infrastructure.inputports;

import com.hrp.restaurant.microservice.restaurant.application.createmenu.CreateMenuRequest;
import jakarta.persistence.EntityNotFoundException;

public interface CreateMenuInputPort {
    void createMenu(CreateMenuRequest createMenuRequest) throws IllegalArgumentException, EntityNotFoundException;
}
