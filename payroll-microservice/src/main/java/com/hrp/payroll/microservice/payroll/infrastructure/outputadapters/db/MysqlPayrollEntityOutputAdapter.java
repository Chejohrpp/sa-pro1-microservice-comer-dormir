package com.hrp.payroll.microservice.payroll.infrastructure.outputadapters.db;

import com.hrp.payroll.microservice.common.annotation.PersistenceAdapter;
import com.hrp.payroll.microservice.payroll.domain.Payroll;
import com.hrp.payroll.microservice.payroll.domain.PayrollEstablishment;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.db.AssignEmployeeEstablishmentOutputPort;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.db.FindPayrollByUsernameAndEstablishment;
import com.hrp.payroll.microservice.payroll.infrastructure.outputports.db.FindPayroolByEstablishmentOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class MysqlPayrollEntityOutputAdapter implements AssignEmployeeEstablishmentOutputPort, FindPayrollByUsernameAndEstablishment, FindPayroolByEstablishmentOutputPort {
    private final JpaPayrollRepository payrollRepository;

    @Autowired
    public MysqlPayrollEntityOutputAdapter(JpaPayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    @Override
    public Payroll save(Payroll payroll) {
        PayrollEntity payrollEntity = PayrollEntity.from(payroll);
        return payrollRepository.save(payrollEntity)
                .toPayroll();
    }

    @Override
    public Optional<Payroll> findPayrollByUsername(String username, String establishment, PayrollEstablishment payrollEstablishment) {
        return payrollRepository.findByEstablishmentAndUsernameAndPayrollEstablishment(establishment, username, payrollEstablishment)
                .map(PayrollEntity::toPayroll);
    }

    @Override
    public List<Payroll> findPayrollByEstablishment(String establishmentCode, PayrollEstablishment payrollEstablishment) {
        return payrollRepository.findByEstablishmentAndPayrollEstablishment(establishmentCode, payrollEstablishment)
                .stream()
                .map(PayrollEntity:: toPayroll)
                .collect(Collectors.toList());
    }
}
