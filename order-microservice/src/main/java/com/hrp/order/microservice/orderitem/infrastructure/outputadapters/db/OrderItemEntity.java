package com.hrp.order.microservice.orderitem.infrastructure.outputadapters.db;

import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.orderitem.domain.OrderItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    private String id;
    @Column(name = "main_order")
    private Long order;
    @Column
    private Long dish;
    @Column
    private Integer quantity;
    @Column
    private Double price;

    public OrderItemEntity(String id, Long order, Long dish, Integer quantity, Double price) {
        this.id = id;
        this.order = order;
        this.dish = dish;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderItemEntity from(OrderItem orderItem) {
        return new OrderItemEntity(
                UUID.randomUUID().toString(),
                orderItem.getOrder().getId(),
                orderItem.getDish(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }

    public OrderItem toDomain(Order order){
        return OrderItem.builder()
                .id(UUID.fromString(id))
                .order(order)
                .dish(dish)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
