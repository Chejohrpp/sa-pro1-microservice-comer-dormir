package com.hrp.restaurant.microservice.restaurant.infrastructure.outputadapter.db;

import com.hrp.restaurant.microservice.restaurant.domain.Restaurant;
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
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String address;

    public static RestaurantEntity from(Restaurant restaurant) {
        return new RestaurantEntity(
                UUID.randomUUID().toString(),
                restaurant.getName(),
                restaurant.getAddress()
        );
    }

    public Restaurant toDomain(){
        return Restaurant.builder()
                .id(UUID.fromString(id))
                .name(name)
                .address(address)
                .build();
    }
}
