package com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi;

public interface CheckRestaurantExistsOutputPort {
    public boolean exists(String restaurantId);
}
