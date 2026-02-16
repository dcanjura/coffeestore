package com.exercise.coffeestore.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Additional {
    private Long id;
    private String description;
    private Double cost;
    private Double combineCost;

    public Additional(Long id, String description, Double cost, Double combineCost) {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.combineCost = combineCost;
    }

    public Double calculateCharge() {
        return switch (description){
            case "Vanilla", "Mint" -> this.cost;
            case "Creamer" -> this.cost * this.combineCost;
            default -> throw new IllegalStateException("Unexpected value: " + description);
        };
    }
}
