package com.hrp.feedback.microservice.review.infrastructure.outputports.restapi;

public interface CheckRestaurantExistsOutputPort {
    public boolean exists(String restaurantId);
}
