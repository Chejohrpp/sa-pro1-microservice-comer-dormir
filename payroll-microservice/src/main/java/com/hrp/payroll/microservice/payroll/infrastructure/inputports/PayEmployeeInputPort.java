package com.hrp.payroll.microservice.payroll.infrastructure.inputports;

import com.hrp.payroll.microservice.common.exceptions.EntityAlreadyExistsException;
import com.hrp.payroll.microservice.payroll.application.paytoemployee.PayEmployeeRequest;
import jakarta.persistence.EntityNotFoundException;

public interface PayEmployeeInputPort {
    void payEmployee(PayEmployeeRequest payEmployeeRequest) throws EntityNotFoundException, EntityAlreadyExistsException, IllegalArgumentException;
}
