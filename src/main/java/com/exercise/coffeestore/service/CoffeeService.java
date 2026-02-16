package com.exercise.coffeestore.service;

import com.exercise.coffeestore.dto.CoffeeDTO;
import com.exercise.coffeestore.exception.CoffeeNotFoundException;
import com.exercise.coffeestore.model.AuditEvent;
import com.exercise.coffeestore.model.Coffee;
import com.exercise.coffeestore.repository.AuditRepository;
import com.exercise.coffeestore.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    private final CoffeeRepository repository;
    private final AuditRepository auditRepository;

    public CoffeeService() {
        this.repository = new CoffeeRepository();
        this.auditRepository = new AuditRepository();
    }

    /**
     * Retrieve a list of all coffees available in the store. Each coffee should include its id, name, description, and enabled status.
     * @return
     */
    public List<CoffeeDTO> getAllCoffees(){
        return repository.getAllCoffees()
                .stream()
                .map(u -> new CoffeeDTO(u.getId(), u.getName(), u.getDescription(), u.isEnabled(), u.getPrice()))
                .collect(Collectors.toList());
    }

    /**
     * Search for a coffee by name or description. The search is case-insensitive and matches either the name or description.
     * @param value
     * @return
     */
    public Optional<CoffeeDTO> getCoffeeByValue(String value){
        return Optional.of(repository.getCoffeeByValue(value)
                .map(u -> new CoffeeDTO(u.getId(), u.getName(), u.getDescription(), u.isEnabled(), u.getPrice()))
                .orElseThrow(() -> new CoffeeNotFoundException("Coffee not found with name or description: " + value)));
    }

    /**
     * Add a new coffee to the store. The request should include the name, description, and enabled status of the coffee.
     * @param name
     * @param description
     * @param enabled
     * @param price
     * @return
     */
    public Optional<CoffeeDTO> createCoffee(String name, String description, Boolean enabled, Double price) {
        return repository.createCoffee(new Coffee(null, name, description, enabled, price))
                .map(u -> {
                    auditRepository.save(new AuditEvent(
                            null,
                            "Added new coffee with name: " + name,
                            LocalDateTime.now(),
                            "Price set: " + price
                    ));

                    return new CoffeeDTO(u.getId(), u.getName(), u.getDescription(), u.isEnabled(), u.getPrice());
                });
    }
}
