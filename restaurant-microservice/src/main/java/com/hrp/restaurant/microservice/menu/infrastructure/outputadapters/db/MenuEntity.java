package com.hrp.restaurant.microservice.menu.infrastructure.outputadapters.db;

import com.hrp.restaurant.microservice.dish.domain.Dish;
import com.hrp.restaurant.microservice.menu.domain.Menu;
import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String restaurant;
    @Column(nullable = false)
    private Long dish;

    public MenuEntity(String restaurant, Long dish) {
        this.restaurant = restaurant;
        this.dish = dish;
    }

    public static MenuEntity from(Menu menu) {
        return new MenuEntity(
                menu.getId(),
                menu.getRestaurant().getId().toString(),
                menu.getDish().getId()
        );
    }

    public Menu toDomain(Restaurant restaurant, Dish dish){
        return Menu.builder()
                .id(id)
                .restaurant(restaurant)
                .dish(dish)
                .build();
    }

    public Menu toDomain(){
        return Menu.builder()
                .id(id)
                .restaurant(Restaurant.builder().id(UUID.fromString(restaurant)).build())
                .dish(Dish.builder().id(dish).build())
                .build();
    }
}
