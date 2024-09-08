package com.hrp.restaurant.microservice.restaurant.infrastructure.inputports;

import com.hrp.restaurant.microservice.common.exceptions.AlreadyExistsException;
import com.hrp.restaurant.microservice.restaurant.application.CreateRestaurant.CreateRestaurantRequest;
import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;

public interface CreateRestaurantInputPort {
    void createRestaurant(CreateRestaurantRequest createRestaurantRequest) throws AlreadyExistsException;
}
