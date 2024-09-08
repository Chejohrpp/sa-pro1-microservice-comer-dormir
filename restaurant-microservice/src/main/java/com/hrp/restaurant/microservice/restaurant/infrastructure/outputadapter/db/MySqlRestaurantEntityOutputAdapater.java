package com.hrp.restaurant.microservice.restaurant.infrastructure.outputadapter.db;

import com.hrp.restaurant.microservice.common.annotation.PersistenceAdapter;
import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;
import com.hrp.restaurant.microservice.restaurant.infrastructure.outputports.CreateRestaurantOutputPort;
import com.hrp.restaurant.microservice.restaurant.infrastructure.outputports.FindRestaurantOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@PersistenceAdapter
public class MySqlRestaurantEntityOutputAdapater implements CreateRestaurantOutputPort, FindRestaurantOutputPort {
    private final JpaRestaurantEntityRepository restaurantEntityRepository;
    private final JpaRestaurantEntityRepository jpaRestaurantEntityRepository;

    @Autowired
    public MySqlRestaurantEntityOutputAdapater(JpaRestaurantEntityRepository restaurantEntityRepository, JpaRestaurantEntityRepository jpaRestaurantEntityRepository) {
        this.restaurantEntityRepository = restaurantEntityRepository;
        this.jpaRestaurantEntityRepository = jpaRestaurantEntityRepository;
    }

    @Override
    public Optional<Restaurant> findByName(String restaurantName) {
        return jpaRestaurantEntityRepository.findByName(restaurantName)
                .map(RestaurantEntity::toDomain);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantEntity restaurantEntity =  RestaurantEntity.from(restaurant);
        return restaurantEntityRepository.save(restaurantEntity)
                .toDomain();
    }

    @Override
    public Optional<Restaurant> findRestaurant(String id) {
        return restaurantEntityRepository.findById(id)
                .map(RestaurantEntity:: toDomain);
    }
}
