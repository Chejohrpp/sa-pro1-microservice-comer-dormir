package com.hrp.order.microservice.payment.infraestructure.inputports;

import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.payment.application.paymentorder.PaymentOrderRequest;

public interface PaymentInputport {
    void savePayment(Order order, PaymentOrderRequest paymentOrderRequest);
}
