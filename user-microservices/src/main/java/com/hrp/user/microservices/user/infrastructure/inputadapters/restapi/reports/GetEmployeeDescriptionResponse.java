package com.hrp.user.microservices.user.infrastructure.inputadapters.restapi.reports;

import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.user.domain.User;
import lombok.Value;

@Value
public class GetEmployeeDescriptionResponse {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private double salary;

    public static GetEmployeeDescriptionResponse from(User user, Employee employee) {
        return new GetEmployeeDescriptionResponse(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                employee.getSalary()
        );
    }
}
