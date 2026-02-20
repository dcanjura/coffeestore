package com.exercise.coffeestore.repository;

import com.exercise.coffeestore.model.AuditEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditEventEntity, Long> {
}
