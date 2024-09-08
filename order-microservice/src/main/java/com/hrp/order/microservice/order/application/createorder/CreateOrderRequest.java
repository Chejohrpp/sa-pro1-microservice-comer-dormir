package com.hrp.order.microservice.order.application.createorder;

import com.hrp.order.microservice.order.application.createorderitem.CreateOrderItemRequest;
import com.hrp.order.microservice.order.domain.Order;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class CreateOrderRequest {
    private String username;
    private String restaurant;
    private LocalDate date;
    private List<CreateOrderItemRequest> items;

    public Order toDomain(){
        return Order.builder()
                .username(username)
                .restaurant(restaurant)
                .date(date)
                .build();
    }
}
