package com.hrp.restaurant.microservice.menu.infrastructure.outputadapters.db;

import com.hrp.restaurant.microservice.dish.infrastructure.outputadapters.db.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaMenuEntityRepository extends JpaRepository<MenuEntity, Long> {
    Optional<MenuEntity> findByRestaurantAndDish(String restaurant, Long dish);

}
