package com.hrp.restaurant.microservice.menu.infrastructure.outputadapters.db;

import com.hrp.restaurant.microservice.common.annotation.PersistenceAdapter;
import com.hrp.restaurant.microservice.menu.domain.Menu;
import com.hrp.restaurant.microservice.menu.infrastructure.outputports.CreateMenuOutputPort;

import java.util.Optional;

@PersistenceAdapter
public class MySqlMenuEntityOutputAdapter implements CreateMenuOutputPort {
    private final JpaMenuEntityRepository jpaMenuEntityRepository;

    public MySqlMenuEntityOutputAdapter(JpaMenuEntityRepository jpaMenuEntityRepository) {
        this.jpaMenuEntityRepository = jpaMenuEntityRepository;
    }

    @Override
    public Menu createMenu(Menu menu) {
        MenuEntity menuEntity =  MenuEntity.from(menu);
        return jpaMenuEntityRepository.save(menuEntity)
                .toDomain(menu.getRestaurant(), menu.getDish());
    }

    @Override
    public Optional<Menu> findByRestaurantAndDish(String restaurant, Long dish) {
        return jpaMenuEntityRepository.findByRestaurantAndDish(restaurant, dish)
                .map(MenuEntity::toDomain);
    }
}
