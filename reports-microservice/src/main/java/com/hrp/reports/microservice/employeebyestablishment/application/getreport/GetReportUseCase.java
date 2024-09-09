package com.hrp.reports.microservice.employeebyestablishment.application.getreport;

import com.hrp.reports.microservice.common.annotation.UseCase;
import com.hrp.reports.microservice.common.enums.Establishment;
import com.hrp.reports.microservice.employeebyestablishment.domain.Employee;
import com.hrp.reports.microservice.employeebyestablishment.domain.EmployeeEstablishment;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputadapters.restapi.employeebyestablishment.EmployeeByEstablishmentResponse;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.inputports.GetReportEmployeeEstablishmentInputPort;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.outputports.restapi.GetDescriptonsEmployeeByUsernameOutputPort;
import com.hrp.reports.microservice.employeebyestablishment.infrastructure.outputports.restapi.GetUsernamesEmployeeByEstablishmentOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
@Transactional
public class GetReportUseCase implements GetReportEmployeeEstablishmentInputPort {
    private final GetDescriptonsEmployeeByUsernameOutputPort getDescriptonsEmployeeByUsernameOutputPort;
    private final GetUsernamesEmployeeByEstablishmentOutputPort getUsernamesEmployeeByEstablishmentOutputPort;

    @Autowired
    public GetReportUseCase(GetDescriptonsEmployeeByUsernameOutputPort getDescriptonsEmployeeByUsernameOutputPort, GetUsernamesEmployeeByEstablishmentOutputPort getUsernamesEmployeeByEstablishmentOutputPort) {
        this.getDescriptonsEmployeeByUsernameOutputPort = getDescriptonsEmployeeByUsernameOutputPort;
        this.getUsernamesEmployeeByEstablishmentOutputPort = getUsernamesEmployeeByEstablishmentOutputPort;
    }

    @Override
    public EmployeeByEstablishmentResponse getReportEmployeeEstablishment(String establishmentId, Establishment type) throws EntityNotFoundException, IllegalArgumentException {
        //get the usernames list of employee by the establishment
        List<String> usernames = getUsernamesEmployeeByEstablishmentOutputPort.getUsernamesEmployeeByEstablishment(establishmentId, type);
        //get the employee description by his usernames
        List<Employee> employeeList =  getDescriptonsEmployeeByUsernameOutputPort.getDescriptonsEmployeeByUsername(usernames);
        return new EmployeeByEstablishmentResponse(establishmentId, employeeList);
    }
}
