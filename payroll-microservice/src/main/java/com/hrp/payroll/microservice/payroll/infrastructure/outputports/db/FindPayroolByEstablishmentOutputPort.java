package com.hrp.payroll.microservice.payroll.infrastructure.outputports.db;

import com.hrp.payroll.microservice.payroll.domain.Payroll;
import com.hrp.payroll.microservice.payroll.domain.PayrollEstablishment;

import java.util.List;
import java.util.Optional;

public interface FindPayroolByEstablishmentOutputPort {
    List<Payroll> findPayrollByEstablishment(String establishmentCode, PayrollEstablishment payrollEstablishment);
}
