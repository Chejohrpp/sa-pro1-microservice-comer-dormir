package com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputports;

import com.hrp.reports.microservice.employeebyestablishment.domain.Employee;
import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.List;

public interface GenerateEmployeeEstablishmentPdfInputPort {
    byte[] generateEmployeeReport(String establishment, List<Employee> employees) throws DocumentException, IOException;
}
