package com.hrp.payroll.microservice.payroll.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payroll {
    private Long id;
    private String username;
    private PayrollEstablishment payrollEstablishment;
    private String establishment;
}
