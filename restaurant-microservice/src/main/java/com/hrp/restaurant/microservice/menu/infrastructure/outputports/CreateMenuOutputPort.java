package com.hrp.restaurant.microservice.menu.infrastructure.outputports;

import com.hrp.restaurant.microservice.menu.domain.Menu;
import com.hrp.restaurant.microservice.menu.infrastructure.outputadapters.db.MenuEntity;

import java.util.Optional;

public interface CreateMenuOutputPort {
    Menu createMenu(Menu menu);

    Optional<Menu> findByRestaurantAndDish(String restaurant, Long dish);
}
