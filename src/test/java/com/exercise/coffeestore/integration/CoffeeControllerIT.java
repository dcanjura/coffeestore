package com.exercise.coffeestore.integration;

import com.exercise.coffeestore.model.CoffeeEntity;
import com.exercise.coffeestore.repository.CoffeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CoffeeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CoffeeRepository repository;

    @Test
    @DisplayName("Should create coffee via API")
    void shouldCreateCoffee() throws Exception {

        String json = """
                {
                    "name": "Latte",
                    "description": "Hot coffee",
                    "enabled": true,
                    "price": 3.5
                }
                """;

        mockMvc.perform(post("/api/v1/coffees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Latte"));
    }

    @Test
    @DisplayName("Should return paged coffees")
    void shouldReturnPagedCoffees() throws Exception {

        repository.save(new CoffeeEntity(null, "Espresso", "Strong", true, 2.0));

        mockMvc.perform(get("/api/v1/coffees?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));
    }
}
