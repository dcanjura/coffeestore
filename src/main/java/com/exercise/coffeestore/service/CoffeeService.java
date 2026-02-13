package com.exercise.coffeestore.service;

import com.exercise.coffeestore.dto.CoffeeDTO;
import com.exercise.coffeestore.exception.CoffeeNotFoundException;
import com.exercise.coffeestore.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    private final CoffeeRepository repository;

    public CoffeeService(CoffeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve a list of all coffees available in the store. Each coffee should include its id, name, description, and enabled status.
     * @return
     */
    public List<CoffeeDTO> getAllCoffees(){
        return repository.getAllCoffees()
                .stream()
                .map(u -> new CoffeeDTO(u.getId(), u.getName(), u.getDescription(), u.isEnabled()))
                .collect(Collectors.toList());
    }

    /**
     * Search for a coffee by name or description. The search is case-insensitive and matches either the name or description.
     * @param value
     * @return
     */
    public Optional<CoffeeDTO> getCoffeeByValue(String value){
        return Optional.of(repository.getCoffeeByValue(value)
                .map(u -> new CoffeeDTO(u.getId(), u.getName(), u.getDescription(), u.isEnabled()))
                .orElseThrow(() -> new CoffeeNotFoundException("Coffee not found with name or description: " + value)));
    }

    /**
     * Add a new coffee to the store. The request should include the name, description, and enabled status of the coffee.
     * @param name
     * @param description
     * @param enabled
     * @return
     */
    public Optional<CoffeeDTO> createCoffee(String name, String description, Boolean enabled) {
        return repository.createCoffee(new com.exercise.coffeestore.model.Coffee(null, name, description, enabled))
                .map(u -> new CoffeeDTO(u.getId(), u.getName(), u.getDescription(), u.isEnabled()));
    }
}
