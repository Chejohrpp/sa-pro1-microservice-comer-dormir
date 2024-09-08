package com.hrp.restaurant.microservice.dish.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaDishEntityRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findByName(String name);
}
