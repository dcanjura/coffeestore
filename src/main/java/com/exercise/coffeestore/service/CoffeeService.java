package com.exercise.coffeestore.service;

import com.exercise.coffeestore.dto.AuditEventDTO;
import com.exercise.coffeestore.dto.CoffeeDTO;
import com.exercise.coffeestore.exception.CoffeeNotFoundException;
import com.exercise.coffeestore.model.CoffeeEntity;
import com.exercise.coffeestore.repository.AuditRepository;
import com.exercise.coffeestore.repository.CoffeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CoffeeService{
    private final CoffeeRepository repository;
    private final AuditRepository auditRepository;

    public CoffeeService(CoffeeRepository repository, AuditRepository auditRepository) {
        this.repository = repository;
        this.auditRepository = auditRepository;
    }

    /**
     * Retrieve a list of all coffees available in the store. Each coffee should include its id, name, description, and enabled status.
     * @param pageable indicates how many items on a single page, avoiding loadness of all records at same time
     * @return
     */
    public Page<CoffeeDTO> getAllCoffees(Pageable pageable){
        return repository.findAll(pageable)
                .map(CoffeeDTO::toDTO);
    }

    /**
     * Search for a coffee by name or description. The search is case-insensitive and matches either the name or description.
     * @param name name of a coffee
     * @param description description of a coffee
     * @return empty value or a coffee
     */
    public Optional<CoffeeDTO> getCoffeeByValue(String name, String description){
        return Optional.of(repository.findByNameOrDescription(name, description )
                .map(u -> new CoffeeDTO(u.getId(), u.getName(), u.getDescription(), u.isEnabled(), u.getPrice()))
                .orElseThrow(() -> new CoffeeNotFoundException("Coffee not found with name or description")));
    }

    /**
     * Add a new coffee to the store. The request should include the name, description, and enabled status of the coffee.
     * @param coffeeDTO value
     * @return optional of coffeeDTO
     */
    public CoffeeDTO createCoffee(CoffeeDTO coffeeDTO) {
        CoffeeEntity coffeeEntity = repository.save(CoffeeDTO.toCoffee(coffeeDTO));
        AuditEventDTO auditEventDTO = new AuditEventDTO(null, "CREATE", LocalDateTime.now(), "New coffee created with price " + coffeeEntity.getPrice());
        auditRepository.save(AuditEventDTO.toDomain(auditEventDTO));
        return CoffeeDTO.toDTO(coffeeEntity);
    }
}
