package com.hrp.restaurant.microservice.restaurant.infrastructure.outputadapter.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRestaurantEntityRepository extends JpaRepository<RestaurantEntity, String> {

    Optional<RestaurantEntity> findByName(String name);

}
