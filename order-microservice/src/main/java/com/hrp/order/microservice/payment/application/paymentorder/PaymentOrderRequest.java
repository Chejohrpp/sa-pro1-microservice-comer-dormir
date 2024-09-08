package com.hrp.order.microservice.payment.application.paymentorder;

import com.hrp.order.microservice.payment.domain.PaymentMethod;
import lombok.Value;

@Value
public class PaymentOrderRequest {
    private Double amount;
    private PaymentMethod paymentMethod;
}
