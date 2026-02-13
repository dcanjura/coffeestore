package com.exercise.coffeestore.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class Order {
    private Long id;
    private String description;
    private List<Coffee> coffees;
    private Integer totalItems;
    private Double total;
    private LocalDateTime date;
}
