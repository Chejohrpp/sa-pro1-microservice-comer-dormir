package com.hrp.payroll.microservice.payroll.application.asignemployeeinestablishmentusecase;

import com.hrp.payroll.microservice.common.annotation.UseCase;
import com.hrp.payroll.microservice.common.exceptions.EntityAlreadyExistsException;
import com.hrp.payroll.microservice.payroll.domain.Payroll;
import com.hrp.payroll.microservice.payroll.domain.PayrollEstablishment;
import com.hrp.payroll.microservice.payroll.infrastructure.inputports.AssignEmployeeEstablishmentInputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.db.AssignEmployeeEstablishmentOutputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.db.FindPayrollByUsernameAndEstablishment;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi.CheckEmployeeExistsOutputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi.CheckHotelExistsOutputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi.CheckRestaurantExistsOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@Transactional
public class AssignEmployeeEstablishmentUseCase implements AssignEmployeeEstablishmentInputPort {
    private final CheckEmployeeExistsOutputPort employeeExistsOutputPort;
    private final CheckHotelExistsOutputPort checkHotelExistsOutputPort;
    private final CheckRestaurantExistsOutputPort checkRestaurantExistsOutputPort;
    private final FindPayrollByUsernameAndEstablishment findPayrollByUsernameAndEstablishment;
    private final AssignEmployeeEstablishmentOutputPort assignEmployeeEstablishmentOutputPort;

    @Autowired
    public AssignEmployeeEstablishmentUseCase(CheckEmployeeExistsOutputPort employeeExistsOutputPort, CheckHotelExistsOutputPort checkHotelExistsOutputPort, CheckRestaurantExistsOutputPort checkRestaurantExistsOutputPort, FindPayrollByUsernameAndEstablishment findPayrollByUsernameAndEstablishment, AssignEmployeeEstablishmentOutputPort assignEmployeeEstablishmentOutputPort) {
        this.employeeExistsOutputPort = employeeExistsOutputPort;
        this.checkHotelExistsOutputPort = checkHotelExistsOutputPort;
        this.checkRestaurantExistsOutputPort = checkRestaurantExistsOutputPort;
        this.findPayrollByUsernameAndEstablishment = findPayrollByUsernameAndEstablishment;
        this.assignEmployeeEstablishmentOutputPort = assignEmployeeEstablishmentOutputPort;
    }

    @Override
    public void assingEmployee(AsignEmployeeEstablishmentRequest request) throws EntityNotFoundException, EntityAlreadyExistsException, IllegalArgumentException {
        //verify if the employee exists
        if(!employeeExistsOutputPort.checkEmployeeExists(request.getUsername())){
            throw new EntityNotFoundException("the employee is not found");
        }
        //verify the type of establishment and then if they exists
        if(request.getPayrollEstablishment().equals(PayrollEstablishment.HOTEL)){
            if(!checkHotelExistsOutputPort.checkHotelExists(request.getEstablishment())){
                throw new IllegalArgumentException("Hotel does not exist");
            }
        } else {
            if(!checkRestaurantExistsOutputPort.exists(request.getEstablishment())){
                throw new IllegalArgumentException("Restaurant does not exist");
            }
        }
        //verify if the current assignment is not yet
        if(findPayrollByUsernameAndEstablishment.findPayrollByUsername(request.getUsername(), request.getEstablishment(), request.getPayrollEstablishment()).isPresent()){
            throw new EntityAlreadyExistsException("the employee already work in this establishment");
        }
        //convert to domain
        Payroll payroll =  request.toDomain();
        //save
        payroll =  assignEmployeeEstablishmentOutputPort.save(payroll);
    }
}
