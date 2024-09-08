package com.hrp.order.microservice.orderitem.infrastructure.outputadapters.db;

import com.hrp.order.microservice.common.annotation.PersistenceAdapter;
import com.hrp.order.microservice.orderitem.domain.OrderItem;
import com.hrp.order.microservice.orderitem.infrastructure.outputports.CreateOrderItemOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

@PersistenceAdapter
public class MySqlOrderItemEntityOutputAdapter implements CreateOrderItemOutputPort {
    private final JpaOrderItemEntityRepository jpaOrderItemEntityRepository;

    @Autowired
    public MySqlOrderItemEntityOutputAdapter(JpaOrderItemEntityRepository jpaOrderItemEntityRepository) {
        this.jpaOrderItemEntityRepository = jpaOrderItemEntityRepository;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        OrderItemEntity orderItemEntity = OrderItemEntity.from(orderItem);
        return jpaOrderItemEntityRepository.save(orderItemEntity)
                .toDomain(orderItem.getOrder());
    }
}
