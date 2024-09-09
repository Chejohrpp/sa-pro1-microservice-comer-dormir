package com.hrp.reports.microservice.employeebyestablishment.infrastructure.outputports.restapi;

import com.hrp.reports.microservice.common.enums.Establishment;

import java.util.List;

public interface GetUsernamesEmployeeByEstablishmentOutputPort {
    List<String> getUsernamesEmployeeByEstablishment(String establishmentId, Establishment type);
}
