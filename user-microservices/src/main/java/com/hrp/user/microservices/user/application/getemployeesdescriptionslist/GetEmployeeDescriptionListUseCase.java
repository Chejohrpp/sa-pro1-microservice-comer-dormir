package com.hrp.user.microservices.user.application.getemployeesdescriptionslist;

import com.hrp.user.microservices.common.annotation.UseCase;
import com.hrp.user.microservices.employee.domain.Employee;
import com.hrp.user.microservices.user.domain.User;
import com.hrp.user.microservices.user.infrastructure.inputadapters.restapi.reports.GetEmployeeDescriptionResponse;
import com.hrp.user.microservices.user.infrastructure.inputports.GetListEmployeesDescriptionInputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.FindEmployeeOutputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.FindUserOutputPort;
import com.hrp.user.microservices.user.infrastructure.outputports.GetListEmployeesDescriptionOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UseCase
@Transactional
public class GetEmployeeDescriptionListUseCase implements GetListEmployeesDescriptionInputPort {
    private final FindEmployeeOutputPort findEmployeeOutputPort;
    private final FindUserOutputPort findUserOutputPort;

    @Autowired
    public GetEmployeeDescriptionListUseCase(FindEmployeeOutputPort findEmployeeOutputPort, FindUserOutputPort findUserOutputPort) {
        this.findEmployeeOutputPort = findEmployeeOutputPort;
        this.findUserOutputPort = findUserOutputPort;
    }

    @Override
    public List<GetEmployeeDescriptionResponse> getListEmployeesDescription(GetEmployeeDescriptionLisRequest getEmployeeDescriptionLisRequest) throws EntityNotFoundException {
        List<GetEmployeeDescriptionResponse> employeeDescriptionResponseList = new ArrayList<>();
        for (String username: getEmployeeDescriptionLisRequest.getUsernames()){
            User user = findUserOutputPort.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            Employee employee =  findEmployeeOutputPort.findEmployeeById(username)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
            employeeDescriptionResponseList.add(GetEmployeeDescriptionResponse.from(user,employee));
        }
        return employeeDescriptionResponseList;
    }
}
