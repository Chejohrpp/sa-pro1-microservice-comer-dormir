package com.hrp.payroll.microservice.payroll.infrastructure.outputadapters.db;

import com.hrp.payroll.microservice.payroll.domain.Payroll;
import com.hrp.payroll.microservice.payroll.domain.PayrollEstablishment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payroll")
@Getter
@Setter
@NoArgsConstructor
public class PayrollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PayrollEstablishment payrollEstablishment;
    @Column(nullable = false)
    private String establishment;

    public PayrollEntity(String username, PayrollEstablishment payrollEstablishment, String establishment) {
        this.username = username;
        this.payrollEstablishment = payrollEstablishment;
        this.establishment = establishment;
    }

    public static PayrollEntity from(Payroll payroll) {
        return new PayrollEntity(
                payroll.getUsername(),
                payroll.getPayrollEstablishment(),
                payroll.getEstablishment()
        );
    }

    public Payroll toPayroll() {
        return Payroll.builder()
                .id(id)
                .establishment(establishment)
                .username(username)
                .payrollEstablishment(payrollEstablishment)
                .build();
    }
}
