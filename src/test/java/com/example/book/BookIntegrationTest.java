// File: src/test/java/com/example/book/BookIntegrationTest.java
package com.example.book;

import com.example.book.entity.Author;
import com.example.book.entity.Book;
import com.example.book.repository.AuthorRepository;
import com.example.book.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testCreateBook() throws Exception {
        Book book = Book.builder()
                .title("Test Book")
                .author(Author.builder().name("Test Author").build())
                .isbn("1234567890")
                .build();

        mockMvc.perform(post("/book-service/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    @Order(2)
    void testGetAllBooks() throws Exception {
        bookRepository.save(Book.builder().title("Book1").author(Author.builder().name("Author1").build()).isbn("111").build());

        mockMvc.perform(get("/book-service/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].title").value("Book1"));
    }

    @Test
    @Order(3)
    void testGetBookById() throws Exception {
        Book saved = bookRepository.save(Book.builder().title("Book2").author(Author.builder().name("Author2").build()).isbn("222").build());

        mockMvc.perform(get("/book-service/api/books/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book2"));
    }

    @Test
    @Order(4)
    void testUpdateBook() throws Exception {
        Book saved = bookRepository.save(Book.builder().title("Book3").author(Author.builder().name("Author3").build()).isbn("333").build());
        saved.setTitle("Updated Book");
        saved.setAuthor(null);
        mockMvc.perform(put("/book-service/api/books/" + saved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saved)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"));
    }

    @Test
    @Order(5)
    void testDeleteBook() throws Exception {
        Book saved = bookRepository.save(Book.builder().title("Book4").author(Author.builder().name("Author4").build()).isbn("444").build());

        mockMvc.perform(delete("/book-service/api/books/" + saved.getId()))
                .andExpect(status().isNoContent());

        Assertions.assertFalse(bookRepository.findById(saved.getId()).isPresent());
    }
}