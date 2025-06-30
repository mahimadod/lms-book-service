package com.example.book.service;

import com.example.book.entity.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookService {
    public Book createBook(Book book) throws SQLException;
    public List<Book> getAllBooks() throws InterruptedException;
    public Optional<Book> getBookById(Long id) throws SQLException;
    public Book updateBook(Long id, Book updatedBook);
    public void deleteBook(Long id);

    List<Book> searchByTitle(String title);
    List<Book> searchByAuthorName(String authorName);
    List<Book> searchByCategoryName(String categoryName);
    Optional<Book> searchByIsbn(String isbn);

}
