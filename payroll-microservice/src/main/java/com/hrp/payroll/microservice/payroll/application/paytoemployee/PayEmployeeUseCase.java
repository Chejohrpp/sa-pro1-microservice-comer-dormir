package com.hrp.payroll.microservice.payroll.application.paytoemployee;

import com.hrp.payroll.microservice.common.annotation.UseCase;
import com.hrp.payroll.microservice.common.exceptions.EntityAlreadyExistsException;
import com.hrp.payroll.microservice.payment.domain.Payment;
import com.hrp.payroll.microservice.payment.infrastructure.outputports.db.RegisterPaymentOutputPort;
import com.hrp.payroll.microservice.payroll.domain.Payroll;
import com.hrp.payroll.microservice.payroll.infrastructure.inputports.PayEmployeeInputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.db.FindPayrollByUsernameAndEstablishment;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.restapi.GetSalaryEmployeeOutputPort;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@UseCase
@Transactional
public class PayEmployeeUseCase implements PayEmployeeInputPort {
    private final FindPayrollByUsernameAndEstablishment findPayrollByUsernameAndEstablishment;
    private final RegisterPaymentOutputPort registerPaymentOutputPort;
    private final GetSalaryEmployeeOutputPort getSalaryEmployeeOutputPort;

    @Autowired
    public PayEmployeeUseCase(FindPayrollByUsernameAndEstablishment findPayrollByUsernameAndEstablishment, RegisterPaymentOutputPort registerPaymentOutputPort, GetSalaryEmployeeOutputPort getSalaryEmployeeOutputPort) {
        this.findPayrollByUsernameAndEstablishment = findPayrollByUsernameAndEstablishment;
        this.registerPaymentOutputPort = registerPaymentOutputPort;
        this.getSalaryEmployeeOutputPort = getSalaryEmployeeOutputPort;
    }

    @Override
    public void payEmployee(PayEmployeeRequest payEmployeeRequest) throws EntityNotFoundException, EntityAlreadyExistsException, IllegalArgumentException {
        //get the payroll
        Payroll payroll = findPayrollByUsernameAndEstablishment.findPayrollByUsername( payEmployeeRequest.getUsername(),
                payEmployeeRequest.getEstablishment(), payEmployeeRequest.getPayrollEstablishment())
                .orElseThrow(() -> new EntityNotFoundException("the employee does not work in the establishment"));

        //get his salary
        double salary = getSalaryEmployeeOutputPort.getSalary(payEmployeeRequest.getUsername());
        //create the payment
        Payment payment =  Payment.builder()
                .payroll(payroll)
                .amount(salary)
                .date(LocalDate.now())
                .build();

        //save the payment
        payment = registerPaymentOutputPort.registerPayment(payment);
    }
}
