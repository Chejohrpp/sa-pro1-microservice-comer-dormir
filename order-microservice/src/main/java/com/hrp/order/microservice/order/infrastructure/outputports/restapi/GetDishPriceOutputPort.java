package com.hrp.order.microservice.order.infrastructure.outputports.restapi;

public interface GetDishPriceOutputPort {
    double getPriceDish(Long dishId);
}
