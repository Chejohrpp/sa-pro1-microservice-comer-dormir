package com.hrp.order.microservice.order.infrastructure.inputports;

import com.hrp.order.microservice.order.application.createorder.CreateOrderRequest;

public interface CreateOrderInputPort {
    void createOrder(CreateOrderRequest createOrderRequest) throws Exception;
}
