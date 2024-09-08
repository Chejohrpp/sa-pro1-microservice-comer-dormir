package com.hrp.user.microservices.user.application.createemployee;

import com.hrp.user.microservices.common.annotation.UserRoleSubset;
import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.employee.domain.EmployeeRole;
import com.hrp.user.microservices.user.application.createuser.CreateUserRequest;
import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    private CreateUserRequest createUserRequest;
    private double salary;
    private LocalDate hireDate;
    //@UserRoleSubset(anyOf = {UserRole.EMPLOYEE, UserRole.MANAGER})
    private EmployeeRole role;

    public Employee toDomain(){
        return Employee.builder()
                .user(createUserRequest.toDomain())
                .salary(salary)
                .hireDate(hireDate)
                .build();
    }

    public Employee toDomain(User user){
        return Employee.builder()
                .user(user)
                .hireDate(hireDate)
                .salary(salary)
                .build();
    }
}
