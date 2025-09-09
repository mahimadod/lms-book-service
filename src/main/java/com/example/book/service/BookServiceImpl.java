package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import com.example.exception_handler.LMSServiceException;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        List<Book> x= bookRepository.findAll();

        MDC.put("response", x.get(0).getTitle());
        return x;
    }

    @Override
    public Optional<Book> getBookById(Long id) throws SQLException {
        return Optional.ofNullable(
                bookRepository.findById(id)
                        .orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND, "Book not found with id: " + id))
        );
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
                .orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND,"Book not found"));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return Optional.ofNullable(bookRepository.findByTitleContainingIgnoreCase(title)).orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND, "Book not found with title: " + title));

    }

    @Override
    public List<Book> searchByAuthorName(String authorName) {
        return Optional.ofNullable(bookRepository.findByAuthor_NameContainingIgnoreCase(authorName)).orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND, "Book not found with author: " + authorName));
    }

    @Override
    public List<Book> searchByCategoryName(String categoryName) {
        return Optional.ofNullable(bookRepository.findByCategory_NameContainingIgnoreCase(categoryName)).orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND, "Book not found with category: " + categoryName));
    }

    @Override
    public Optional<Book> searchByIsbn(String isbn) {
        return Optional.ofNullable(bookRepository.findByIsbn(isbn)).orElseThrow(() -> new LMSServiceException(HttpStatus.NOT_FOUND, "Book not found with isbn: " + isbn));
    }
}
