package com.hrp.user.microservices.employee.infrastructure.outputports;

import com.hrp.user.microservices.employee.domain.Employee;

public interface CreateEmployeeOutputPort {
    Employee save(Employee employee);
}
