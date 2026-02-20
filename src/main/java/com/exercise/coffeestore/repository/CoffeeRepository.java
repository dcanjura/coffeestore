package com.exercise.coffeestore.repository;

import com.exercise.coffeestore.model.CoffeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoffeeRepository extends JpaRepository<CoffeeEntity, Long> {
    public Optional<CoffeeEntity> findByNameOrDescription(String name, String description);
}
