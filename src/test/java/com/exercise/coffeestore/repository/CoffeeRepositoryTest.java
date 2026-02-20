package com.exercise.coffeestore.repository;

import com.exercise.coffeestore.model.CoffeeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CoffeeRepositoryTest {

    @Autowired
    private CoffeeRepository repository;

    @Test
    @DisplayName("Should save coffee correctly")
    void shouldSaveCoffee() {

        CoffeeEntity coffee = new CoffeeEntity(
                null,
                "Latte",
                "Hot coffee",
                true,
                3.5
        );

        CoffeeEntity saved = repository.save(coffee);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Latte");
    }

    @Test
    @DisplayName("Should find coffee by name")
    void shouldFindByName() {

        CoffeeEntity coffee = new CoffeeEntity(
                null,
                "Espresso",
                "Strong",
                true,
                2.0
        );

        repository.save(coffee);

        Optional<CoffeeEntity> result =
                repository.findByNameOrDescription("Espresso", "Strong");

        assertThat(result).isPresent();
        assertThat(result.get().getDescription()).isEqualTo("Strong");
    }
}
