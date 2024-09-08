package com.hrp.restaurant.microservice.dish.infrastructure.outputports;

import com.hrp.restaurant.microservice.dish.domain.Dish;

import java.util.Optional;

public interface FinddishOutputPort {
    Optional<Dish> findDishById(Long id);
}
