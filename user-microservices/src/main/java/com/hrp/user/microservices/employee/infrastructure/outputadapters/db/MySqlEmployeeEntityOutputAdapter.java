package com.hrp.user.microservices.employee.infrastructure.outputadapters.db;

import com.hrp.user.microservices.common.annotation.PersistenceAdapter;
import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.employee.infrastructure.outputports.CreateEmployeeOutputPort;

@PersistenceAdapter
public class MySqlEmployeeEntityOutputAdapter implements CreateEmployeeOutputPort {
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
}
