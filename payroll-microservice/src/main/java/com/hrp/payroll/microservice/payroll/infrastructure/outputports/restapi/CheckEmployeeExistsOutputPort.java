package com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi;

public interface CheckEmployeeExistsOutputPort {
    public boolean checkEmployeeExists(String username);
}
