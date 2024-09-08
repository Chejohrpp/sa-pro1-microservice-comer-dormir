package com.hrp.order.microservice.order.infrastructure.inputadpaters.restapi;

import lombok.Value;

@Value
public class OrderResponse {
    String message;

    public static OrderResponse of(String message) {
        return new OrderResponse(message);
    }

}
