package com.hrp.payroll.microservice.payment.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentEntityRepository extends JpaRepository<PaymentEnity, String> {

}
