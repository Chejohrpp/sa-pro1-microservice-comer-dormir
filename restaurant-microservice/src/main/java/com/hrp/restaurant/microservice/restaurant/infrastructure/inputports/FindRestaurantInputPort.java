package com.hrp.restaurant.microservice.restaurant.infrastructure.inputports;

import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;

import java.util.Optional;

public interface FindRestaurantInputPort {
    Optional<Restaurant> findRestaurant(String id);
}
