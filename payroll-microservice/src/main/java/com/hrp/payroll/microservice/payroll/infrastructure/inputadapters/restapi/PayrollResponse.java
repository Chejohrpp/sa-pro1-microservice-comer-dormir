package com.hrp.payroll.microservice.payroll.infrastructure.inputadapters.restapi;

import lombok.Value;

@Value
public class PayrollResponse {
    private String message;
    public static PayrollResponse of(String message) {
        return new PayrollResponse(message);
    }
}
