package com.hrp.restaurant.microservice.restaurant.infrastructure.outputports;

import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;

import java.util.Optional;

public interface FindRestaurantOutputPort {
    Optional<Restaurant> findRestaurant(String id);
}
