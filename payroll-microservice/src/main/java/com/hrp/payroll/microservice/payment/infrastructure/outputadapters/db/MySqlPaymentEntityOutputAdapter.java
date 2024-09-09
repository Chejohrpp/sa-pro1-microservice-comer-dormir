package com.hrp.payroll.microservice.payment.infrastructure.outputadapters.db;

import com.hrp.payroll.microservice.common.annotation.PersistenceAdapter;
import com.hrp.payroll.microservice.payment.domain.Payment;
import com.hrp.payroll.microservice.payment.infrastructure.outputports.db.RegisterPaymentOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

@PersistenceAdapter
public class MySqlPaymentEntityOutputAdapter implements RegisterPaymentOutputPort {
    private final JpaPaymentEntityRepository paymentEntityRepository;

    @Autowired
    public MySqlPaymentEntityOutputAdapter(JpaPaymentEntityRepository paymentEntityRepository) {
        this.paymentEntityRepository = paymentEntityRepository;
    }

    @Override
    public Payment registerPayment(Payment payment) {
        PaymentEnity paymentEnity = PaymentEnity.from(payment);
        return paymentEntityRepository.save(paymentEnity)
                .toDomain(payment.getPayroll());
    }
}
