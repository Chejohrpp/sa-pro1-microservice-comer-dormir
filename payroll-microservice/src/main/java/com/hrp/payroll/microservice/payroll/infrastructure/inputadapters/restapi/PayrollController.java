package com.hrp.payroll.microservice.payroll.infrastructure.inputadapters.restapi;

import com.hrp.payroll.microservice.common.exceptions.EntityAlreadyExistsException;
import com.hrp.payroll.microservice.payroll.application.asignemployeeinestablishmentusecase.AsignEmployeeEstablishmentRequest;
import com.hrp.payroll.microservice.payroll.application.paytoemployee.PayEmployeeRequest;
import com.hrp.payroll.microservice.payroll.infrastructure.inputports.AssignEmployeeEstablishmentInputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.inputports.PayEmployeeInputPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/payrolls")
public class PayrollController {
    private final AssignEmployeeEstablishmentInputPort assignEmployeeEstablishmentInputPort;
    private final PayEmployeeInputPort payEmployeeInputPort;

    @Autowired
    public PayrollController(AssignEmployeeEstablishmentInputPort assignEmployeeEstablishmentInputPort, PayEmployeeInputPort payEmployeeInputPort) {
        this.assignEmployeeEstablishmentInputPort = assignEmployeeEstablishmentInputPort;
        this.payEmployeeInputPort = payEmployeeInputPort;
    }

    @PostMapping("/create")
    public ResponseEntity<PayrollResponse> assignEmployeeEstablishmentInputPort
            (@RequestBody AsignEmployeeEstablishmentRequest request) throws EntityAlreadyExistsException {
        assignEmployeeEstablishmentInputPort.assingEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PayrollResponse.of("the employee was assign into the establishment"));
    }

    @PostMapping("/pay")
    public ResponseEntity<PayrollResponse> payEmployeeInputPort
            (@RequestBody PayEmployeeRequest request) throws EntityNotFoundException, EntityAlreadyExistsException, IllegalArgumentException {
        payEmployeeInputPort.payEmployee(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(PayrollResponse.of("the employee was paid"));
    }

}
