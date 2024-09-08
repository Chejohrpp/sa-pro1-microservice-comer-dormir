package com.hrp.restaurant.microservice.menu.domain;

import com.hrp.restaurant.microservice.dish.domain.Dish;
import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Menu {
    private Long id;
    private Restaurant restaurant;
    private Dish dish;
}
