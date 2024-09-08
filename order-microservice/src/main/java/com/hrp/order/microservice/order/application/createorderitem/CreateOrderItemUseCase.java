package com.hrp.order.microservice.order.application.createorderitem;

import com.hrp.order.microservice.common.annotation.UseCase;
import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.orderitem.domain.OrderItem;
import com.hrp.order.microservice.orderitem.infrastructure.inputports.CreateOrderItemInputPort;
import com.hrp.order.microservice.orderitem.infrastructure.outputports.CreateOrderItemOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@Transactional
public class CreateOrderItemUseCase implements CreateOrderItemInputPort {
    private final CreateOrderItemOutputPort createOrderItemOutputPort;

    @Autowired
    public CreateOrderItemUseCase(CreateOrderItemOutputPort createOrderItemOutputPort) {
        this.createOrderItemOutputPort = createOrderItemOutputPort;
    }

    @Override
    public void createOrderItem(Order order, OrderItem orderItem) throws IllegalArgumentException {
        orderItem.setOrder(order);
        if (orderItem.getQuantity() < 0){
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        orderItem = createOrderItemOutputPort.save(orderItem);
    }
}
