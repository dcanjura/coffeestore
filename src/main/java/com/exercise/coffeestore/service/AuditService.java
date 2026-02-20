package com.exercise.coffeestore.service;

import com.exercise.coffeestore.dto.AuditEventDTO;
import com.exercise.coffeestore.repository.AuditRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditService {

    private AuditRepository repository;

    public AuditService(AuditRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve all audit events registered by the app
     * @return
     */
    public Page<AuditEventDTO> getAuditEvents(Pageable pageable) {
        return repository.findAll(pageable)
                .map(AuditEventDTO::toDTO);
    }
}
