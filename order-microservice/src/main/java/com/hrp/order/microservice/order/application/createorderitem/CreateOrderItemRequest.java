package com.hrp.order.microservice.order.application.createorderitem;

import com.hrp.order.microservice.orderitem.domain.OrderItem;
import lombok.Value;

@Value
public class CreateOrderItemRequest {
    private Long dish;
    private Integer quantity;

    public OrderItem toDomain(double price){
        return OrderItem.builder()
                .dish(dish)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
