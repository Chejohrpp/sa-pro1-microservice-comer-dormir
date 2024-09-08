package com.hrp.order.microservice.order.infrastructure.outputports;

import com.hrp.order.microservice.order.domain.Order;

import java.util.Optional;

public interface FindOrderOutputPort {
    Optional<Order> findOrder(Long orderId);
}
