package com.hrp.order.microservice.payment.infraestructure.outputadapter.db;


import com.hrp.order.microservice.common.annotation.PersistenceAdapter;
import com.hrp.order.microservice.payment.domain.Payment;
import com.hrp.order.microservice.payment.infraestructure.outputports.PaymentOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

@PersistenceAdapter
public class MysqlPaymentEntityOutputAdapter implements PaymentOutputPort {
    private final JpaPaymentEntityRepository jpaPaymentEntityRepository;

    @Autowired
    public MysqlPaymentEntityOutputAdapter(JpaPaymentEntityRepository jpaPaymentEntityRepository) {
        this.jpaPaymentEntityRepository = jpaPaymentEntityRepository;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity paymentEntity = PaymentEntity.from(payment);
        return jpaPaymentEntityRepository.save(paymentEntity).toDomain(payment.getOrder());
    }
}
