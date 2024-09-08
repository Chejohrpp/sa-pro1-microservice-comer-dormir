package com.hrp.order.microservice.order.infrastructure.outputports;

import com.hrp.order.microservice.order.domain.Order;

public interface PayOrderOutputPort {
    Order updateOrderWithPayments(Order order);
}
