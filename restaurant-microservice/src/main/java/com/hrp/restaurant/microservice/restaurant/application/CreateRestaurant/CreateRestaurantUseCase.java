package com.hrp.restaurant.microservice.restaurant.application.CreateRestaurant;

import com.hrp.restaurant.microservice.common.annotation.UseCase;
import com.hrp.restaurant.microservice.common.exceptions.AlreadyExistsException;
import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;
import com.hrp.restaurant.microservice.restaurant.infrastructure.inputports.CreateRestaurantInputPort;
import com.hrp.restaurant.microservice.restaurant.infrastructure.outputports.CreateRestaurantOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@Transactional
public class CreateRestaurantUseCase implements CreateRestaurantInputPort {
    private final CreateRestaurantOutputPort createRestaurantOutputPort;

    @Autowired
    public CreateRestaurantUseCase(CreateRestaurantOutputPort createRestaurantOutputPort) {
        this.createRestaurantOutputPort = createRestaurantOutputPort;
    }

    @Override
    public void createRestaurant(CreateRestaurantRequest createRestaurantRequest) throws AlreadyExistsException {
        //verify if not exists
        if (createRestaurantOutputPort.findByName(createRestaurantRequest.getName()).isPresent()) {
            throw new AlreadyExistsException("Restaurant already exists");
        }

        //convert to domain
        Restaurant restaurant = createRestaurantRequest.toDomain();

        //save
        restaurant = createRestaurantOutputPort.save(restaurant);

    }
}
