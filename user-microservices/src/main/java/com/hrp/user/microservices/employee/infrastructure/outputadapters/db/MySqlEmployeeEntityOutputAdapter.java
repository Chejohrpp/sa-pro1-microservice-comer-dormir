package com.hrp.user.microservices.employee.infrastructure.outputadapters.db;

import com.hrp.user.microservices.client.domain.Client;
import com.hrp.user.microservices.common.annotation.PersistenceAdapter;
import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.employee.infrastructure.outputports.CreateEmployeeOutputPort;
import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.infrastructure.outputports.FindClientOutputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.FindEmployeeOutputPort;

import java.util.Optional;

@PersistenceAdapter
public class MySqlEmployeeEntityOutputAdapter implements CreateEmployeeOutputPort, FindEmployeeOutputPort {
    private final JpaEmployeeEnityRepository jpaEmployeeEnityRepository;

    public MySqlEmployeeEntityOutputAdapter(JpaEmployeeEnityRepository jpaEmployeeEnityRepository) {
        this.jpaEmployeeEnityRepository = jpaEmployeeEnityRepository;
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity employeeEntity = EmployeeEntity.from(employee);
        return jpaEmployeeEnityRepository.save(employeeEntity)
                .toDomain(employee.getUser());
    }

    @Override
    public Optional<Employee> findEmployeeById(String username) {
        return jpaEmployeeEnityRepository.findById(username)
                .map(employeeEntity -> employeeEntity.toDomain(User.builder().username(employeeEntity.getUsername()).build()));
    }
}
