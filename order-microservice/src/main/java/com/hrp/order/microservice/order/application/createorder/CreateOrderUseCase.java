package com.hrp.order.microservice.order.application.createorder;

import com.hrp.order.microservice.common.annotation.UseCase;
import com.hrp.order.microservice.order.application.createorderitem.CreateOrderItemRequest;
import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.order.domain.OrderStatus;
import com.hrp.order.microservice.order.infrastructure.inputports.CreateOrderInputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.CreateOrderOutputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.restapi.CheckMenuDishRestaurantOutputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.restapi.CheckUsernameExistsOutputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.restapi.GetDishPriceOutputPort;
import com.hrp.order.microservice.orderitem.domain.OrderItem;
import com.hrp.order.microservice.orderitem.infrastructure.inputports.CreateOrderItemInputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UseCase
@Transactional
public class CreateOrderUseCase implements CreateOrderInputPort {
    private final CreateOrderOutputPort createOrderOutputPort;
    private final CheckUsernameExistsOutputPort checkUsernameExistsOutputPort;
    private final CheckMenuDishRestaurantOutputPort checkMenuDishRestaurantOutputPort;
    private final GetDishPriceOutputPort getDishPriceOutputPort;
    private final CreateOrderItemInputPort createOrderItemInputPort;

    @Autowired
    public CreateOrderUseCase(CreateOrderOutputPort createOrderOutputPort, CheckUsernameExistsOutputPort checkUsernameExistsOutputPort, CheckMenuDishRestaurantOutputPort checkMenuDishRestaurantOutputPort, GetDishPriceOutputPort getDishPriceOutputPort, CreateOrderItemInputPort createOrderItemInputPort) {
        this.createOrderOutputPort = createOrderOutputPort;
        this.checkUsernameExistsOutputPort = checkUsernameExistsOutputPort;
        this.checkMenuDishRestaurantOutputPort = checkMenuDishRestaurantOutputPort;
        this.getDishPriceOutputPort = getDishPriceOutputPort;
        this.createOrderItemInputPort = createOrderItemInputPort;
    }

    @Override
    public void createOrder(CreateOrderRequest createOrderRequest) throws Exception {
        //conver to domain
        Order order = createOrderRequest.toDomain();
        order.setTotalAmount(0);
        order.setStatus(OrderStatus.PENDING);
        //validates
        //verify if the user exists
        if(!checkUsernameExistsOutputPort.checkUserExists(order.getUsername())){
            throw new EntityNotFoundException("the client is not exists");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        //verify if the dish exists in the restaurant and get the price
        for (CreateOrderItemRequest orderItemRequest: createOrderRequest.getItems()){
            if(!checkMenuDishRestaurantOutputPort.existsDishInRestaurant(order.getRestaurant(), orderItemRequest.getDish())){
                throw new EntityNotFoundException("the dish is not exists in the restaurant");
            }
            double priceDish = getDishPriceOutputPort.getPriceDish(orderItemRequest.getDish());
            orderItems.add(orderItemRequest.toDomain(priceDish));
            order.setTotalAmount(order.getTotalAmount() + (priceDish * orderItemRequest.getQuantity()));
        }

        //save the order
        order = createOrderOutputPort.save(order);

        //save the items
        for (OrderItem orderItem: orderItems){
            createOrderItemInputPort.createOrderItem(order, orderItem);
        }

    }
}
