package com.exercise.coffeestore.controller;

import com.exercise.coffeestore.dto.AuditEventDTO;
import com.exercise.coffeestore.service.AuditService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public ResponseEntity<Page<AuditEventDTO>> getAuditEvents(Pageable pageable) {
        return ResponseEntity.ok(auditService.getAuditEvents(pageable));
    }
}
