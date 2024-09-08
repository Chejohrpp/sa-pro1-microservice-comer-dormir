package com.hrp.order.microservice.order.infrastructure.inputadpaters.restapi;

import com.hrp.order.microservice.order.application.createorder.CreateOrderRequest;
import com.hrp.order.microservice.order.application.payorder.PayOrderRequest;
import com.hrp.order.microservice.order.infrastructure.inputports.CreateOrderInputPort;
import com.hrp.order.microservice.order.infrastructure.inputports.PayOrderInputPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/orders")
public class OrderController {
    private final CreateOrderInputPort createOrderInputPort;
    private final PayOrderInputPort payOrderInputPort;

    public OrderController(CreateOrderInputPort createOrderInputPort, PayOrderInputPort payOrderInputPort) {
        this.createOrderInputPort = createOrderInputPort;
        this.payOrderInputPort = payOrderInputPort;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws Exception {
        createOrderInputPort.createOrder(createOrderRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(OrderResponse.of("taken the order, to start the cooking"));
    }

    @PostMapping("/pay/{orderid}")
    public ResponseEntity<OrderResponse> payOrder(
            @PathVariable Long orderid,
            @RequestBody PayOrderRequest payOrderRequest) throws Exception {
        payOrderInputPort.payOrder(orderid, payOrderRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(OrderResponse.of("the food already pay, hope you ATE"));
    }

}
