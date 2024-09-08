package com.hrp.user.microservices.user.application.getemployee;

import com.hrp.user.microservices.common.annotation.UseCase;
import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.user.infrastructure.inputports.FindEmployeeInputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.FindEmployeeOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@UseCase
@Transactional
public class GetEmployeeUseCase implements FindEmployeeInputPort {
    private final FindEmployeeOutputPort findEmployeeOutputPort;

    @Autowired
    public GetEmployeeUseCase(FindEmployeeOutputPort findEmployeeOutputPort) {
        this.findEmployeeOutputPort = findEmployeeOutputPort;
    }

    @Override
    public Optional<Employee> findEmployee(String username) {
        return findEmployeeOutputPort.findEmployeeById(username);
    }
}
