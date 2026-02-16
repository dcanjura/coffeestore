package com.exercise.coffeestore.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Coffee {
    private Long id;
    private String name;
    private String description;
    private boolean enabled;
    private Double price;

    public Coffee(Long id, String name, String description, boolean enabled, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.price = price;
    }
}
