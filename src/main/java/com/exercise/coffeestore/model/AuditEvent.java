package com.exercise.coffeestore.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AuditEvent {

    private Long id;
    private String action;
    private LocalDateTime date;
    private String description;

    public AuditEvent(Long id, String action, LocalDateTime date, String description) {
        this.id = id;
        this.action = action;
        this.date = date;
        this.description = description;
    }
}
