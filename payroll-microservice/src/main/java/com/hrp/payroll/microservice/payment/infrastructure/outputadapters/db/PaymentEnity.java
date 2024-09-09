package com.hrp.payroll.microservice.payment.infrastructure.outputadapters.db;

import com.hrp.payroll.microservice.payment.domain.Payment;
import com.hrp.payroll.microservice.payroll.domain.Payroll;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
public class PaymentEnity {
    @Id
    private String id;
    @Column
    private Long payroll;
    @Column
    private LocalDate date;
    @Column
    private double amount;

    public PaymentEnity(String id, Long payroll, LocalDate date, double amount) {
        this.id = id;
        this.payroll = payroll;
        this.date = date;
        this.amount = amount;
    }

    public static PaymentEnity from(Payment payment) {
        return new PaymentEnity(
                UUID.randomUUID().toString(),
                payment.getPayroll().getId(),
                payment.getDate(),
                payment.getAmount()
        );
    }

    public Payment toDomain(Payroll payroll){
        return Payment.builder()
                .id(UUID.fromString(id))
                .payroll(payroll)
                .date(date)
                .amount(amount)
                .build();
    }
}
