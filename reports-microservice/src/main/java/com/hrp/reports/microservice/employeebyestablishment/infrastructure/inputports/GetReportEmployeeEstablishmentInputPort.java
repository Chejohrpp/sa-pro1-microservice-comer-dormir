package com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputports;

import com.hrp.reports.microservice.common.enums.Establishment;
import com.hrp.reports.microservice.employeebyestablishment.domain.EmployeeEstablishment;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputadapters.restapi.employeebyestablishment.EmployeeByEstablishmentResponse;
import jakarta.persistence.EntityNotFoundException;

public interface GetReportEmployeeEstablishmentInputPort {
    EmployeeByEstablishmentResponse getReportEmployeeEstablishment(String establishmentId, Establishment type) throws EntityNotFoundException, IllegalArgumentException;
}
