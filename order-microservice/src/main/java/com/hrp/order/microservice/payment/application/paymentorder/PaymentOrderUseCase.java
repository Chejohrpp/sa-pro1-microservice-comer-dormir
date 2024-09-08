package com.hrp.order.microservice.payment.application.paymentorder;

import com.hrp.order.microservice.common.annotation.UseCase;
import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.payment.domain.Payment;
import com.hrp.order.microservice.payment.domain.PaymentStatus;
import com.hrp.order.microservice.payment.infraestructure.inputports.PaymentInputport;
import com.hrp.order.microservice.payment.infraestructure.outputports.PaymentOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@UseCase
@Transactional
public class PaymentOrderUseCase implements PaymentInputport {
    private final PaymentOutputPort paymentOutputPort;

    @Autowired
    public PaymentOrderUseCase(PaymentOutputPort paymentOutputPort) {
        this.paymentOutputPort = paymentOutputPort;
    }

    @Override
    public void savePayment(Order order, PaymentOrderRequest paymentOrderRequest) {
        Payment payment =  Payment.builder()
                .order(order)
                .paymentDate(LocalDate.now())
                .amount(paymentOrderRequest.getAmount())
                .status(PaymentStatus.APPROVED)
                .method(paymentOrderRequest.getPaymentMethod())
                .build();
       payment =  paymentOutputPort.save(payment);
    }
}
