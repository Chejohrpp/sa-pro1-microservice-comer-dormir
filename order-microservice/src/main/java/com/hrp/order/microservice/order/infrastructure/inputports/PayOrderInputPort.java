package com.hrp.order.microservice.order.infrastructure.inputports;

import com.hrp.order.microservice.order.application.payorder.PayOrderRequest;

public interface PayOrderInputPort {
    void payOrder(Long orderId, PayOrderRequest payOrderRequest);
}
