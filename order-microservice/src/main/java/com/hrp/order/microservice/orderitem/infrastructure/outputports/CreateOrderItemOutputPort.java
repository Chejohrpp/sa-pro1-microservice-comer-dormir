package com.hrp.order.microservice.orderitem.infrastructure.outputports;

import com.hrp.order.microservice.orderitem.domain.OrderItem;

public interface CreateOrderItemOutputPort {
    OrderItem save(OrderItem orderItem);
}
