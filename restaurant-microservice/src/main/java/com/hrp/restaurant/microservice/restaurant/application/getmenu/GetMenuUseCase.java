package com.hrp.restaurant.microservice.restaurant.application.getmenu;

import com.hrp.restaurant.microservice.common.annotation.UseCase;
import com.hrp.restaurant.microservice.menu.domain.Menu;
import com.hrp.restaurant.microservice.menu.infrastructure.outputports.CreateMenuOutputPort;
import com.hrp.restaurant.microservice.restaurant.infrastructure.inputports.FindMenuInputPort;
import jakarta.transaction.Transactional;

import java.util.Optional;

@UseCase
@Transactional
public class GetMenuUseCase implements FindMenuInputPort {
    private final CreateMenuOutputPort createMenuOutputPort;

    public GetMenuUseCase(CreateMenuOutputPort createMenuOutputPort) {
        this.createMenuOutputPort = createMenuOutputPort;
    }

    @Override
    public Optional<Menu> findMenuByName(String restaurant, Long dish) {
        return createMenuOutputPort.findByRestaurantAndDish(restaurant, dish);
    }
}
