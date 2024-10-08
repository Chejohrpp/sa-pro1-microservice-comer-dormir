package com.hrp.order.microservice.orderitem.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderItemEntityRepository extends JpaRepository<OrderItemEntity, String> {

}
