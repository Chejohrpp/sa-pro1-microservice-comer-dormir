package com.hrp.user.microservices.user.application.getsalaryemployee;

import com.hrp.user.microservices.common.annotation.UseCase;
import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.user.infrastructure.inputports.GetSalaryEmployeeInuputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.FindEmployeeOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@Transactional
public class GetSalaryEmployeeUseCase implements GetSalaryEmployeeInuputPort {
    private final FindEmployeeOutputPort findEmployeeOutputPort;

    @Autowired
    public GetSalaryEmployeeUseCase(FindEmployeeOutputPort findEmployeeOutputPort) {
        this.findEmployeeOutputPort = findEmployeeOutputPort;
    }

    @Override
    public double getSalaryEmployeeInuput(String username) throws EntityNotFoundException {
        //get the employee
        Employee employee =  findEmployeeOutputPort.findEmployeeById(username)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return employee.getSalary();
    }
}
