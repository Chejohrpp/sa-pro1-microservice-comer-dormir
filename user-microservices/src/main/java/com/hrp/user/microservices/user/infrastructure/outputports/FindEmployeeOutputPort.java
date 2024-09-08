package com.hrp.user.microservices.user.infrastructure.outputports;

import com.hrp.user.microservices.employee.domain.Employee;

import java.util.Optional;

public interface FindEmployeeOutputPort {
    Optional<Employee> findEmployeeById(String username);
}
