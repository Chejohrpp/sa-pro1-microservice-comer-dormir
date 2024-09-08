package com.hrp.order.microservice.order.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderEntityRepository extends JpaRepository<OrderEntity, Long> {
}
