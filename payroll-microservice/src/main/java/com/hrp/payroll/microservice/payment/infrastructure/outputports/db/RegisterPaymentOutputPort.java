package com.hrp.payroll.microservice.payment.infrastructure.outputports.db;

import com.hrp.payroll.microservice.payment.domain.Payment;

public interface RegisterPaymentOutputPort {
    Payment registerPayment(Payment payment);
}
