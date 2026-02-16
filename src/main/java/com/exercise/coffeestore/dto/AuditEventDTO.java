package com.exercise.coffeestore.dto;

import java.time.LocalDateTime;

public record AuditEventDTO(Long id, String action, LocalDateTime date, String description) {
}
