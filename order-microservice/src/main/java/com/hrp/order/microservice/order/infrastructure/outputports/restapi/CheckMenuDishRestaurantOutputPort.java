package com.hrp.order.microservice.order.infrastructure.outputports.restapi;

public interface CheckMenuDishRestaurantOutputPort {
    boolean existsDishInRestaurant(String restaurantId, Long dishId);
}
