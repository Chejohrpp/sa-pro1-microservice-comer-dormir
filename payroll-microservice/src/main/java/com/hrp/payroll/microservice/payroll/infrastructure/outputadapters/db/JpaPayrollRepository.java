package com.hrp.payroll.microservice.payroll.infrastructure.outputadapters.db;

import com.hrp.payroll.microservice.payroll.domain.Payroll;
import com.hrp.payroll.microservice.payroll.domain.PayrollEstablishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPayrollRepository extends JpaRepository<PayrollEntity, Long> {
    Optional<PayrollEntity> findByEstablishmentAndUsernameAndPayrollEstablishment(String establishment, String username, PayrollEstablishment payrollEstablishment);

    List<PayrollEntity> findByEstablishmentAndPayrollEstablishment(String establishment, PayrollEstablishment payrollEstablishment);
}
