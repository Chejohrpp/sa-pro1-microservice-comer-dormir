package com.hrp.restaurant.microservice.dish.infrastructure.outputadapters.db;

import com.hrp.restaurant.microservice.common.annotation.PersistenceAdapter;
import com.hrp.restaurant.microservice.dish.domain.Dish;
import com.hrp.restaurant.microservice.dish.infrastructure.outputports.CreateDishOutputPort;
import com.hrp.restaurant.microservice.dish.infrastructure.outputports.FinddishOutputPort;
import com.hrp.restaurant.microservice.restaurant.infrastructure.outputports.FindPriceDishOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@PersistenceAdapter
public class MySqlDishEnityOutputAdapter implements CreateDishOutputPort, FindPriceDishOutputPort, FinddishOutputPort {
    private final JpaDishEntityRepository jpaDishEntityRepository;

    @Autowired
    public MySqlDishEnityOutputAdapter(JpaDishEntityRepository jpaDishEntityRepository) {
        this.jpaDishEntityRepository = jpaDishEntityRepository;
    }

    @Override
    public Dish save(Dish dish) {
        DishEntity dishEntity = DishEntity.from(dish);
        return jpaDishEntityRepository.save(dishEntity)
                .toDomain();
    }

    @Override
    public Optional<Dish> findByName(String dishName) {
        return jpaDishEntityRepository.findByName(dishName)
                .map(DishEntity::toDomain);
    }

    @Override
    public Optional<Dish> findPriceDishById(Long id) {
        return jpaDishEntityRepository.findById(id)
                .map(DishEntity :: toDomain );
    }

    @Override
    public Optional<Dish> findDishById(Long id) {
        return jpaDishEntityRepository.findById(id)
                .map(DishEntity :: toDomain );
    }
}
