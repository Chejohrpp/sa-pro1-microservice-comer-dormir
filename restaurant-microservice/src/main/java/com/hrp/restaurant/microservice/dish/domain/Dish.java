package com.hrp.restaurant.microservice.dish.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Dish {
    private Long id;
    private String name;
    private double price;

}
