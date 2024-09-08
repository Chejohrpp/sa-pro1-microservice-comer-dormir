package com.hrp.order.microservice.order.infrastructure.outputadapters.db;

import com.hrp.order.microservice.common.annotation.PersistenceAdapter;
import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.order.infrastructure.outputports.CreateOrderOutputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.FindOrderOutputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.PayOrderOutputPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@PersistenceAdapter
public class MySqlOrderEntityOutputAdapter implements CreateOrderOutputPort, PayOrderOutputPort, FindOrderOutputPort {
    private final JpaOrderEntityRepository jpaOrderEntityRepository;

    @Autowired
    public MySqlOrderEntityOutputAdapter(JpaOrderEntityRepository jpaOrderEntityRepository) {
        this.jpaOrderEntityRepository = jpaOrderEntityRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderEntity.from(order);
        return jpaOrderEntityRepository.save(orderEntity)
                .todDomain();
    }

    @Override
    public Order updateOrderWithPayments(Order order) {
        OrderEntity orderEntity = jpaOrderEntityRepository.findById(order.getId())
                .orElseThrow(() -> new EntityNotFoundException("order not found"));
        orderEntity.setStatus(order.getStatus());
        return  jpaOrderEntityRepository.save(orderEntity)
                .todDomain();
    }

    @Override
    public Optional<Order> findOrder(Long orderId) {
        return jpaOrderEntityRepository.findById(orderId)
                .map(OrderEntity::todDomain);
    }
}
