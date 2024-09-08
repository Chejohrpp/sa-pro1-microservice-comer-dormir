package com.hrp.user.microservices.employee.domain;

import com.hrp.user.microservices.user.domain.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Employee {
    private User user;
    private double salary;
    private LocalDate hireDate;

    public boolean isSalaryPositive() {
        return salary > 0;
    }
}
