package com.hrp.restaurant.microservice.restaurant.application.createmenu;

import com.hrp.restaurant.microservice.menu.domain.Menu;
import lombok.Value;

@Value
public class CreateMenuRequest {
    private String restaurant;
    private long dish;

}
