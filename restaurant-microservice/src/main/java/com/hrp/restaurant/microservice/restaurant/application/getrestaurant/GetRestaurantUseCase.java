package com.hrp.restaurant.microservice.restaurant.application.getrestaurant;

import com.hrp.restaurant.microservice.common.annotation.UseCase;
import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;
import com.hrp.restaurant.microservice.restaurant.infrastructure.inputports.FindRestaurantInputPort;
import com.hrp.restaurant.microservice.restaurant.infrastructure.outputports.FindRestaurantOutputPort;
import jakarta.transaction.Transactional;

import java.util.Optional;

@UseCase
@Transactional
public class GetRestaurantUseCase implements FindRestaurantInputPort {
    private final FindRestaurantOutputPort findRestaurantOutputPort;

    public GetRestaurantUseCase(FindRestaurantOutputPort findRestaurantOutputPort) {
        this.findRestaurantOutputPort = findRestaurantOutputPort;
    }

    @Override
    public Optional<Restaurant> findRestaurant(String id) {
        return findRestaurantOutputPort.findRestaurant(id);
    }
}
