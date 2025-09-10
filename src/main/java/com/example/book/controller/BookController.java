package com.example.book.controller;

import com.example.book.entity.Book;
import com.example.book.service.BookService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/books")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BookController.class);

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) throws SQLException {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() throws InterruptedException {
        ResponseEntity<List<Book>> x= ResponseEntity.ok(bookService.getAllBooks());
        MDC.put("userId", "abc123");
        logger.info("Handling request");
        return x;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) throws SQLException {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
