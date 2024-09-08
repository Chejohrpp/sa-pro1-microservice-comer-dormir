package com.hrp.order.microservice.order.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Order {
    private Long id;
    private String username;
    private String restaurant;
    private LocalDate date;
    private double totalAmount;
    private OrderStatus status;
}
