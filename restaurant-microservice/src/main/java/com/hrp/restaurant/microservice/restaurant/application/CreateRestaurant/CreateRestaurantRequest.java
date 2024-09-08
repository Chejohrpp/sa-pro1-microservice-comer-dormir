package com.hrp.restaurant.microservice.restaurant.application.CreateRestaurant;

import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;
import lombok.Value;

@Value
public class CreateRestaurantRequest {
    public String name;
    public String address;

    public Restaurant toDomain(){
        return Restaurant.builder()
                .name(name)
                .address(address)
                .build();
    }
}
