package com.hrp.user.microservices.user.infrastructure.inputports;

import jakarta.persistence.EntityNotFoundException;

public interface GetSalaryEmployeeInuputPort {
    double getSalaryEmployeeInuput(String username) throws EntityNotFoundException;
}
