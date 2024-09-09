package com.hrp.reports.microservice.employeebyestablishment.infrastructure.outputports.restapi;

import com.hrp.reports.microservice.employeebyestablishment.domain.Employee;

import java.util.List;

public interface GetDescriptonsEmployeeByUsernameOutputPort {
    List<Employee> getDescriptonsEmployeeByUsername(List<String> usernames);
}
