package com.hrp.payroll.microservice.payroll.infrastructure.outputports.db;

import com.hrp.payroll.microservice.payroll.domain.Payroll;

public interface AssignEmployeeEstablishmentOutputPort {
    Payroll save(Payroll payroll);
}
