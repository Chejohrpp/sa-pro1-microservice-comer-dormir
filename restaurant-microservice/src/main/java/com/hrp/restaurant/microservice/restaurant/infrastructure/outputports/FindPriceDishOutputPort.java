package com.hrp.restaurant.microservice.restaurant.infrastructure.outputports;

import com.hrp.restaurant.microservice.dish.domain.Dish;

import java.util.Optional;

public interface FindPriceDishOutputPort {
    Optional<Dish> findPriceDishById(Long id);
}
