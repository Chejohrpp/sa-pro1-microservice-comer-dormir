package com.hrp.payroll.microservice.payroll.application.getemployeessestablishment;

import com.hrp.payroll.microservice.common.annotation.UseCase;
import com.hrp.payroll.microservice.payroll.domain.Payroll;
import com.hrp.payroll.microservice.payroll.domain.PayrollEstablishment;
import com.hrp.payroll.microservice.payroll.infrastructure.inputadapters.restapi.report6.findemployeessestablishment.FindEmployeesEstablishmentResponse;
import com.hrp.payroll.microservice.payroll.infrastructure.inputports.FindEmployeessEstablishmentInputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.db.FindPayroolByEstablishmentOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional
public class GetListEmployeessEstablishmentUseCase implements FindEmployeessEstablishmentInputPort {
    private final FindPayroolByEstablishmentOutputPort findPayroolByEstablishmentOutputPort;

    @Autowired
    public GetListEmployeessEstablishmentUseCase(FindPayroolByEstablishmentOutputPort findPayroolByEstablishmentOutputPort) {
        this.findPayroolByEstablishmentOutputPort = findPayroolByEstablishmentOutputPort;
    }

    @Override
    public FindEmployeesEstablishmentResponse findEmployees(String establishment, PayrollEstablishment payrollEstablishment) throws EntityNotFoundException {
        List<Payroll> payrolls =  findPayroolByEstablishmentOutputPort.findPayrollByEstablishment(establishment, payrollEstablishment);

        // 2. Extraer los usernames de los objetos Payroll usando Java Streams
        List<String> usernames = payrolls.stream()
                .map(Payroll::getUsername) // Extraer el username de cada objeto Payroll
                .collect(Collectors.toList());

        // 3. Retornar la respuesta con la lista de usernames
        return new FindEmployeesEstablishmentResponse(usernames);
    }
}
