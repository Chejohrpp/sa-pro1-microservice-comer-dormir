package com.hrp.user.microservices.user.application.createemployee;

import com.hrp.user.microservices.common.annotation.UseCase;
import com.hrp.user.microservices.common.exceptions.AlreadyExistsException;
import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.employee.domain.EmployeeRole;
import com.hrp.user.microservices.employee.infrastructure.outputports.CreateEmployeeOutputPort;
import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.domain.UserRole;
import com.hrp.user.microservices.user.infrastructure.inputports.CreateEmployeeInputPort;
import com.hrp.user.microservices.user.infrastructure.inputports.CreateUserInputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@Transactional
public class CreateEmployeeUseCase implements CreateEmployeeInputPort {
    private final CreateUserInputPort createUserInputPort;
    private final CreateEmployeeOutputPort createEmployeeOutputPort;

    @Autowired
    public CreateEmployeeUseCase(CreateUserInputPort createUserInputPort, CreateEmployeeOutputPort createEmployeeOutputPort) {
        this.createUserInputPort = createUserInputPort;
        this.createEmployeeOutputPort = createEmployeeOutputPort;
    }

    @Override
    public void createEmployee(CreateEmployeeRequest createEmployeeRequest) throws AlreadyExistsException, IllegalArgumentException {
        //save as user
        User user = createUserInputPort.createUser(createEmployeeRequest.getCreateUserRequest(), mapEmployeeRoleToUserRole(createEmployeeRequest.getRole()));

        //convert to domain
        Employee employee = createEmployeeRequest.toDomain(user);

        //verify the salary is positive
        if (!employee.isSalaryPositive()){
            throw new IllegalArgumentException("Salary cannot be negative");
        }

        //save
        createEmployeeOutputPort.save(employee);
    }

    private UserRole mapEmployeeRoleToUserRole(EmployeeRole employeeRole) {
        return switch (employeeRole) {
            case MANAGER -> UserRole.MANAGER;
            case EMPLOYEE -> UserRole.EMPLOYEE;
            default -> throw new IllegalArgumentException("No matching UserRole for EmployeeRole: " + employeeRole);
        };
    }

}
