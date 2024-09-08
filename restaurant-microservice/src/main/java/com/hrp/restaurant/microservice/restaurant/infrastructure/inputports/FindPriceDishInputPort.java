package com.hrp.restaurant.microservice.restaurant.infrastructure.inputports;

import com.hrp.restaurant.microservice.dish.domain.Dish;
import jakarta.persistence.EntityNotFoundException;

public interface FindPriceDishInputPort {
    double findPriceDish(Long dishId) throws EntityNotFoundException;
}
