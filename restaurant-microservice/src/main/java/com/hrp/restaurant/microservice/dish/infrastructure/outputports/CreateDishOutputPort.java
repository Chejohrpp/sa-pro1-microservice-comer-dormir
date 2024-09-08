package com.hrp.restaurant.microservice.dish.infrastructure.outputports;

import com.hrp.restaurant.microservice.dish.domain.Dish;

import java.util.Optional;

public interface CreateDishOutputPort {
    Dish save(Dish dish);

    Optional<Dish> findByName(String dishName);
}
