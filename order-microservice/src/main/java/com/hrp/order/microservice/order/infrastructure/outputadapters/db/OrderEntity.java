package com.hrp.order.microservice.order.infrastructure.outputadapters.db;

import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.order.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "order_c")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String restaurant;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private double totalAmount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderEntity(String username, String restaurant, LocalDate date, double totalAmount, OrderStatus status) {
        this.username = username;
        this.restaurant = restaurant;
        this.date = date;
        this.totalAmount = totalAmount;
        this.status = status;
    }

     public static OrderEntity from(Order order) {
        return new OrderEntity(
                order.getUsername(),
                order.getRestaurant(),
                order.getDate(),
                order.getTotalAmount(),
                order.getStatus()
        );
     }

     public Order todDomain() {
        return Order.builder()
                .id(id)
                .username(username)
                .restaurant(restaurant)
                .date(date)
                .totalAmount(totalAmount)
                .status(status)
                .build();
     }
}
