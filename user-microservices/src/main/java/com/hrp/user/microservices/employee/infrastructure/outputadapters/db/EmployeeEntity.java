package com.hrp.user.microservices.employee.infrastructure.outputadapters.db;

import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @Id
    private String username;

    private double salary;

    private LocalDate hireDate;

    public static EmployeeEntity from(Employee employee) {
        return new EmployeeEntity(
                employee.getUser().getUsername(),
                employee.getSalary(),
                employee.getHireDate()
        );
    }

    public Employee toDomain(User user) {
        return Employee.builder()
                .user(user)
                .salary(salary)
                .hireDate(hireDate)
                .build();
    }
}
