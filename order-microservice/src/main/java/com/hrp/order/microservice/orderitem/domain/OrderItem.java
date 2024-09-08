package com.hrp.order.microservice.orderitem.domain;

import com.hrp.order.microservice.order.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderItem {
    private UUID id;
    private Order order;
    private Long dish;
    private Integer quantity;
    private Double price;
}
