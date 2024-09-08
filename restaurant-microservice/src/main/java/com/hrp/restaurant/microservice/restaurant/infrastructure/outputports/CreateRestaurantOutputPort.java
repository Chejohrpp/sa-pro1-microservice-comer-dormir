package com.hrp.restaurant.microservice.restaurant.infrastructure.outputports;

import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;

import java.util.Optional;

public interface CreateRestaurantOutputPort {
    Optional<Restaurant> findByName(String restaurantName);

    Restaurant save(Restaurant restaurant);
}
