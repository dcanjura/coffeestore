package com.exercise.coffeestore.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Additional {
    private Long id;
    private String description;
    private Double cost;
    private Double combineCost;
}
