package com.hrp.reports.microservice.employeebyestablishment.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeEstablishment {
    private String establishmentName;
    private String establishmentAddress;
    private List<Employee> employees;
}
