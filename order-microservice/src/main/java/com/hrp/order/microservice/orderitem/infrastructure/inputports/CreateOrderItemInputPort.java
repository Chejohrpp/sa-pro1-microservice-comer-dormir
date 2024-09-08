package com.hrp.order.microservice.orderitem.infrastructure.inputports;

import com.hrp.order.microservice.order.application.createorderitem.CreateOrderItemRequest;
import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.orderitem.domain.OrderItem;

public interface CreateOrderItemInputPort {
    void createOrderItem(Order order, OrderItem orderItem) throws IllegalArgumentException;
}
