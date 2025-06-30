package com.example.book.service;


import com.example.book.entity.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);
    List<Author> getAllAuthors();
}
