package com.hrp.payroll.microservice.payment.domain;

import com.hrp.payroll.microservice.payroll.domain.Payroll;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class Payment {
    private UUID id;
    private Payroll payroll;
    private LocalDate date;
    private double amount;
}
