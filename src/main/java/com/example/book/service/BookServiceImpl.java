package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) throws SQLException {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() throws InterruptedException {
        System.out.println("book");
        Thread.sleep(2000);
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) throws SQLException {
        return bookRepository.findById(id);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setIsbn(updatedBook.getIsbn());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setPublisher(updatedBook.getPublisher());
                    book.setCategory(updatedBook.getCategory());
                    book.setTotalCopies(updatedBook.getTotalCopies());
                    book.setAvailableCopies(updatedBook.getAvailableCopies());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> searchByAuthorName(String authorName) {
        return bookRepository.findByAuthor_NameContainingIgnoreCase(authorName);
    }

    @Override
    public List<Book> searchByCategoryName(String categoryName) {
        return bookRepository.findByCategory_NameContainingIgnoreCase(categoryName);
    }

    @Override
    public Optional<Book> searchByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
}
