package com.exercise.coffeestore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "additional")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Additional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double cost;
    private Double combineCost;

    public Double calculateCharge() {
        return switch (description){
            case "Vanilla", "Mint" -> this.cost;
            case "Creamer" -> this.cost * this.combineCost;
            default -> throw new IllegalStateException("Unexpected value: " + description);
        };
    }
}
