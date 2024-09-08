package com.hrp.user.microservices.employee.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployeeEnityRepository extends JpaRepository<EmployeeEntity, String> {
}
