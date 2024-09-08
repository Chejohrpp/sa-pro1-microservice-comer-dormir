package com.hrp.order.microservice.payment.domain;

import com.hrp.order.microservice.order.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Payment {
    private Long id;
    private Order order;
    private Double amount;
    private LocalDate paymentDate;
    private PaymentStatus status;
    private PaymentMethod method;
}
