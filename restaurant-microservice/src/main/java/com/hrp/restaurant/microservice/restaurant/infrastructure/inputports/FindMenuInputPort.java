package com.hrp.restaurant.microservice.restaurant.infrastructure.inputports;

import com.hrp.restaurant.microservice.menu.domain.Menu;

import java.util.Optional;

public interface FindMenuInputPort {

    Optional<Menu> findMenuByName(String restaurant, Long dish);
}
