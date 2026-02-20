package com.exercise.coffeestore.dto;

import com.exercise.coffeestore.model.AuditEventEntity;
import java.time.LocalDateTime;

public record AuditEventDTO(Long id, String action, LocalDateTime date, String description) {
    public static AuditEventEntity toDomain(AuditEventDTO auditEventDTO) {
        return new AuditEventEntity(auditEventDTO.id, auditEventDTO.action, auditEventDTO.date, auditEventDTO.description);
    }

    public static AuditEventDTO toDTO(AuditEventEntity auditEventEntity) {
        return new AuditEventDTO(
                auditEventEntity.getId(),
                auditEventEntity.getAction(),
                auditEventEntity.getDate(),
                auditEventEntity.getDescription()
        );
    }
}
