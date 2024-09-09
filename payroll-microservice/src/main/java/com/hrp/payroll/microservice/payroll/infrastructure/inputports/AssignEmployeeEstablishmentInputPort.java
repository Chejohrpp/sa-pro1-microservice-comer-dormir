package com.hrp.payroll.microservice.payroll.infrastructure.inputports;

import com.hrp.payroll.microservice.common.exceptions.EntityAlreadyExistsException;
import com.hrp.payroll.microservice.payroll.application.asignemployeeinestablishmentusecase.AsignEmployeeEstablishmentRequest;
import jakarta.persistence.EntityNotFoundException;

public interface AssignEmployeeEstablishmentInputPort {
    void assingEmployee(AsignEmployeeEstablishmentRequest request) throws EntityNotFoundException, EntityAlreadyExistsException, IllegalArgumentException;
}
