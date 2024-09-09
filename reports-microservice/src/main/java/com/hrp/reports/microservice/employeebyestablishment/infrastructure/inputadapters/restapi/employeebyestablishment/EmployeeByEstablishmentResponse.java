package com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputadapters.restapi.employeebyestablishment;

import com.hrp.reports.microservice.employeebyestablishment.domain.Employee;
import lombok.Value;

import java.util.List;

@Value
public class EmployeeByEstablishmentResponse {
    String establishmentId;
    List<Employee> employees;
}
