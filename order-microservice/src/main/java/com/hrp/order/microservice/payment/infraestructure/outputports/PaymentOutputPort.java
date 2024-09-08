package com.hrp.order.microservice.payment.infraestructure.outputports;


import com.hrp.order.microservice.payment.domain.Payment;

public interface PaymentOutputPort {
    Payment save(Payment payment);
}
