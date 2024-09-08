package com.hrp.order.microservice.payment.infraestructure.outputadapter.db;


import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.payment.domain.Payment;
import com.hrp.order.microservice.payment.domain.PaymentMethod;
import com.hrp.order.microservice.payment.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "main_order", nullable = false)
    private Long order;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    public PaymentEntity(long order, Double amount, LocalDate paymentDate, PaymentStatus status, PaymentMethod method) {
        this.order = order;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
        this.method = method;
    }

    public static PaymentEntity from(Payment payment) {
        return new PaymentEntity(
                payment.getOrder().getId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getStatus(),
                payment.getMethod()
        );
    }

    public Payment toDomain(Order order){
        return Payment.builder()
                .id(id)
                .order(order)
                .amount(amount)
                .paymentDate(paymentDate)
                .status(status)
                .method(method)
                .build();
    }


}
