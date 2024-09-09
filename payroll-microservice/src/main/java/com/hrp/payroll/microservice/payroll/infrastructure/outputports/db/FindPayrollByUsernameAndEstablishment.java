package com.hrp.payroll.microservice.payroll.infrastructure.outputports.db;

import com.hrp.payroll.microservice.payroll.domain.Payroll;
import com.hrp.payroll.microservice.payroll.domain.PayrollEstablishment;

import java.util.Optional;

public interface FindPayrollByUsernameAndEstablishment {
    Optional<Payroll> findPayrollByUsername(String username, String establishment, PayrollEstablishment payrollEstablishment);
}
