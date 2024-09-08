package com.hrp.restaurant.microservice.restaurant.infrastructure.inputadapters.restapi;

import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;
import lombok.Value;

@Value
public class RestaurantResponse {
    private String message;

    public static RestaurantResponse of(String message) {
        return new RestaurantResponse(message);
    }
}
