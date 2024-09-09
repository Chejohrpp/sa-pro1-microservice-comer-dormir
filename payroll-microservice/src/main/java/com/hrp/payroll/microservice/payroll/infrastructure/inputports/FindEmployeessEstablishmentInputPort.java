package com.hrp.payroll.microservice.payroll.infrastructure.inputports;

import com.hrp.payroll.microservice.payroll.domain.PayrollEstablishment;
import com.hrp.payroll.microservice.payroll.infrastructure.inputadapters.restapi.report6.findemployeessestablishment.FindEmployeesEstablishmentResponse;
import jakarta.persistence.EntityNotFoundException;

public interface FindEmployeessEstablishmentInputPort {
    FindEmployeesEstablishmentResponse findEmployees(String establishment, PayrollEstablishment payrollEstablishment) throws EntityNotFoundException;
}
