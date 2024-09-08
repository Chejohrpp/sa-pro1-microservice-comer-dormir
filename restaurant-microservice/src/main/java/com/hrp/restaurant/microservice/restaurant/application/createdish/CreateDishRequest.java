package com.hrp.restaurant.microservice.restaurant.application.createdish;

import com.hrp.restaurant.microservice.dish.domain.Dish;
import lombok.Value;

@Value
public class CreateDishRequest {
    private String name;
    private double price;

    public Dish toDomain(){
        return Dish.builder().name(name).price(price).build();
    }
}
