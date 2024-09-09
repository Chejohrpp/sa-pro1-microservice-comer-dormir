package com.hrp.reports.microservice.employeebyestablishment.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private double salary;
}
