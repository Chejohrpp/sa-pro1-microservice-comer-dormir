package com.hrp.restaurant.microservice.dish.infrastructure.outputadapters.db;

import com.hrp.restaurant.microservice.dish.domain.Dish;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    public static DishEntity from(Dish dish) {
        return new DishEntity(
                dish.getId(),
                dish.getName(),
                dish.getPrice()
        );
    }

    public Dish toDomain(){
        return Dish.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }

}
