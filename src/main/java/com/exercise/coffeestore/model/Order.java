package com.exercise.coffeestore.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class Order {
    private Long id;
    private String description;
    List<Map<Coffee, List<Additional>>> coffees;
    private Integer totalItems;
    private Double total;
    private LocalDateTime date;

    public Order(Long id, String description, List<Map<Coffee, List<Additional>>> coffees, Integer totalItems, Double total, LocalDateTime date) {
        this.id = id;
        this.description = description;
        this.coffees = coffees;
        this.totalItems = totalItems;
        this.total = total;
        this.date = date;
    }
}
