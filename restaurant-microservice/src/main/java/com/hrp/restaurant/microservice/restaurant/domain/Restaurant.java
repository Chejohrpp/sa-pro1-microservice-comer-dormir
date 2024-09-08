package com.hrp.restaurant.microservice.restaurant.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Restaurant {
    private UUID id;
    private String name;
    private String address;

}
