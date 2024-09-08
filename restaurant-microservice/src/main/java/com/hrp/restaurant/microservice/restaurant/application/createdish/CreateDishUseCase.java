package com.hrp.restaurant.microservice.restaurant.application.createdish;

import com.hrp.restaurant.microservice.common.annotation.UseCase;
import com.hrp.restaurant.microservice.common.exceptions.AlreadyExistsException;
import com.hrp.restaurant.microservice.dish.domain.Dish;
import com.hrp.restaurant.microservice.dish.infrastructure.outputports.CreateDishOutputPort;
import com.hrp.restaurant.microservice.restaurant.infrastructure.inputports.CreateDishInputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@Transactional
public class CreateDishUseCase implements CreateDishInputPort {
    private final CreateDishOutputPort createDishOutputPort;

    @Autowired
    public CreateDishUseCase(CreateDishOutputPort createDishOutputPort) {
        this.createDishOutputPort = createDishOutputPort;
    }

    @Override
    public void createDish(CreateDishRequest createDishRequest) throws AlreadyExistsException {
        //verify if name exists
        if (createDishOutputPort.findByName(createDishRequest.getName()).isPresent()) {
            throw new AlreadyExistsException("Dish with name " + createDishRequest.getName() + " already exists");
        }

        //convert to domain
        Dish dish = createDishRequest.toDomain();

        //save
        dish = createDishOutputPort.save(dish);
    }
}
