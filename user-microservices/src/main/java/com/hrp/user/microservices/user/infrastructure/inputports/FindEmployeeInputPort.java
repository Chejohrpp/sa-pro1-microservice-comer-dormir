package com.hrp.user.microservices.user.infrastructure.inputports;

import com.hrp.user.microservices.employee.domain.Employee;

import java.util.Optional;

public interface FindEmployeeInputPort {
    Optional<Employee> findEmployee(String username);
}
