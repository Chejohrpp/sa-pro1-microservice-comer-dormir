package com.hrp.reports.microservice.employeebyestablishment.application.getreport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeDescriptionLisRequest {
    private List<String> usernames;
}
