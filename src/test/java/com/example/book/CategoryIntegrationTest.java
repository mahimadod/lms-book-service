package com.example.book;

import com.example.book.entity.Category;
import com.example.book.repository.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void testCreateCategory() throws Exception {
        String categoryJson = objectMapper.writeValueAsString(
            Map.of("name", "Fiction")
        );

        mockMvc.perform(post("/book-service/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect(jsonPath("$.name").value("Fiction"));
    }

    @Test
    @Order(2)
    void testGetAllCategories() throws Exception {
        categoryRepository.save(Category.builder().name("Science").build());

        mockMvc.perform(get("/book-service/api/categories"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", notNullValue()));
    }
}