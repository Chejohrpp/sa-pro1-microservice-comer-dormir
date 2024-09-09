package com.hrp.user.microservices.user.infrastructure.inputports;

import com.hrp.user.microservices.user.application.getemployeesdescriptionslist.GetEmployeeDescriptionLisRequest;
import com.hrp.user.microservices.user.infrastructure.inputadapters.restapi.reports.GetEmployeeDescriptionResponse;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface GetListEmployeesDescriptionInputPort {
    List<GetEmployeeDescriptionResponse> getListEmployeesDescription(GetEmployeeDescriptionLisRequest getEmployeeDescriptionLisRequest) throws EntityNotFoundException;
}
