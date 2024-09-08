package com.hrp.restaurant.microservice.restaurant.infrastructure.inputadapters.restapi;

import com.hrp.restaurant.microservice.common.exceptions.AlreadyExistsException;
import com.hrp.restaurant.microservice.restaurant.application.CreateRestaurant.CreateRestaurantRequest;
import com.hrp.restaurant.microservice.restaurant.application.createdish.CreateDishRequest;
import com.hrp.restaurant.microservice.restaurant.application.createmenu.CreateMenuRequest;
import com.hrp.restaurant.microservice.restaurant.infrastructure.inputports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/restaurants")
public class RestaurantController {
    private final CreateRestaurantInputPort createRestaurantInputPort;
    private final CreateDishInputPort createDishInputPort;
    private final FindPriceDishInputPort findPriceDishInputPort;
    private final CreateMenuInputPort createMenuInputPort;
    private final FindMenuInputPort findMenuInputPort;

    @Autowired
    public RestaurantController(CreateRestaurantInputPort createRestaurantInputPort, CreateDishInputPort createDishInputPort, FindPriceDishInputPort findPriceDishInputPort, CreateMenuInputPort createMenuInputPort, FindMenuInputPort findMenuInputPort) {
        this.createRestaurantInputPort = createRestaurantInputPort;
        this.createDishInputPort = createDishInputPort;
        this.findPriceDishInputPort = findPriceDishInputPort;
        this.createMenuInputPort = createMenuInputPort;
        this.findMenuInputPort = findMenuInputPort;
    }

    @PostMapping("/create")
    public ResponseEntity<RestaurantResponse> createRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest) throws AlreadyExistsException {
        createRestaurantInputPort.createRestaurant(createRestaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RestaurantResponse.of("Restaurant created successfully"));
    }

    @PostMapping("/dish/create")
    public ResponseEntity<RestaurantResponse> createDish(@RequestBody CreateDishRequest createDishRequest) throws AlreadyExistsException {
        createDishInputPort.createDish(createDishRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RestaurantResponse.of("Dish created successfully"));
    }

    @GetMapping("/dish/{id}/price")
    public ResponseEntity<Double> getPriceDish(@PathVariable Long id) {
        double price = findPriceDishInputPort.findPriceDish(id);
        return ResponseEntity.status(HttpStatus.OK).body(price);
    }

    @PostMapping("/menu/create")
    public ResponseEntity<RestaurantResponse> createMenu(@RequestBody CreateMenuRequest createMenuRequest) throws Exception {
        createMenuInputPort.createMenu(createMenuRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RestaurantResponse.of("Menu created successfully"));
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/existing-menu")
    public ResponseEntity<Void> existsMenu(@RequestParam("restaurantid") String restaurantId,
                                           @RequestParam("dishid") Long dishid) throws Exception {
        if(findMenuInputPort.findMenuByName(restaurantId, dishid).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
