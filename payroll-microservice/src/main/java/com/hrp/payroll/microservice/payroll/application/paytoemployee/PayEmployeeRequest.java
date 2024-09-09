package com.hrp.payroll.microservice.payroll.application.paytoemployee;

import com.hrp.payroll.microservice.payroll.domain.Payroll;
import com.hrp.payroll.microservice.payroll.domain.PayrollEstablishment;
import lombok.Value;

@Value
public class PayEmployeeRequest {
    private String username;
    private PayrollEstablishment payrollEstablishment;
    private String establishment;

    public Payroll toDomain(){
        return Payroll.builder()
                .username(username)
                .payrollEstablishment(payrollEstablishment)
                .establishment(establishment).build();
    }
}
