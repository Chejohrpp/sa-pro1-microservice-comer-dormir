package com.hrp.restaurant.microservice.restaurant.application.createmenu;

import com.hrp.restaurant.microservice.common.annotation.UseCase;
import com.hrp.restaurant.microservice.dish.domain.Dish;
import com.hrp.restaurant.microservice.dish.infrastructure.outputports.FinddishOutputPort;
import com.hrp.restaurant.microservice.menu.domain.Menu;
import com.hrp.restaurant.microservice.menu.infrastructure.outputports.CreateMenuOutputPort;
import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;
import com.hrp.restaurant.microservice.restaurant.infrastructure.inputports.CreateMenuInputPort;
import com.hrp.restaurant.microservice.restaurant.infrastructure.outputports.FindRestaurantOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@Transactional
public class CreateMenuUseCase implements CreateMenuInputPort {
    private final CreateMenuOutputPort createMenuOutputPort;
    private final FindRestaurantOutputPort findRestaurantOutputPort;
    private final FinddishOutputPort finddishOutputPort;

    @Autowired
    public CreateMenuUseCase(CreateMenuOutputPort createMenuOutputPort, FindRestaurantOutputPort findRestaurantOutputPort, FinddishOutputPort finddishOutputPort) {
        this.createMenuOutputPort = createMenuOutputPort;
        this.findRestaurantOutputPort = findRestaurantOutputPort;
        this.finddishOutputPort = finddishOutputPort;
    }

    @Override
    public void createMenu(CreateMenuRequest createMenuRequest) throws IllegalArgumentException, EntityNotFoundException {
        //find the restaurant
        Restaurant restaurant =  findRestaurantOutputPort.findRestaurant(createMenuRequest.getRestaurant())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        // find the dish
        Dish dish = finddishOutputPort.findDishById(createMenuRequest.getDish())
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));
        //verify if together
        if(createMenuOutputPort.findByRestaurantAndDish(createMenuRequest.getRestaurant(), createMenuRequest.getDish()).isPresent()){
            throw new IllegalArgumentException("Dish already exists in the restaurant");
        }
        //convert the domain
        Menu menu = Menu.builder()
                .dish(dish)
                .restaurant(restaurant)
                .build();
        //save
        menu = createMenuOutputPort.createMenu(menu);
    }
}
