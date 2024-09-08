package com.hrp.restaurant.microservice.restaurant.application.getpriceDish;

import com.hrp.restaurant.microservice.common.annotation.UseCase;
import com.hrp.restaurant.microservice.dish.domain.Dish;
import com.hrp.restaurant.microservice.restaurant.infrastructure.inputports.FindPriceDishInputPort;
import com.hrp.restaurant.microservice.restaurant.infrastructure.outputports.FindPriceDishOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@Transactional
public class GetPriceDishUseCase implements FindPriceDishInputPort {
    private final FindPriceDishOutputPort findPriceDishOutputPort;

    @Autowired
    public GetPriceDishUseCase(FindPriceDishOutputPort findPriceDishOutputPort) {
        this.findPriceDishOutputPort = findPriceDishOutputPort;
    }

    @Override
    public double findPriceDish(Long dishId) throws EntityNotFoundException {
        //find dish
        Dish dish = findPriceDishOutputPort.findPriceDishById(dishId)
                .orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        //return the price
        return dish.getPrice();
    }
}
