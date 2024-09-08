package com.hrp.user.microservices.user.infrastructure.inputports;

import com.hrp.user.microservices.common.exceptions.AlreadyExistsException;
import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.user.application.createemployee.CreateEmployeeRequest;

public interface CreateEmployeeInputPort {
    void createEmployee(CreateEmployeeRequest createEmployeeRequest) throws AlreadyExistsException, IllegalArgumentException;
}
