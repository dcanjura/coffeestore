package com.exercise.coffeestore.repository;

import com.exercise.coffeestore.model.AuditEvent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuditRepository {

    private Long idCounter = 1L;
    private final List<AuditEvent>  auditEvents = new ArrayList<>();

    public void save(AuditEvent auditEvent){
        AuditEvent event = new AuditEvent(idCounter++, auditEvent.getAction(), auditEvent.getDate(), auditEvent.getDescription());
    }

    public List<AuditEvent> getAuditEvents(){
        return List.copyOf(auditEvents);
    }
}
