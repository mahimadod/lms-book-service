package com.example.book;

import com.example.book.entity.Author;
import com.example.book.repository.AuthorRepository;
import com.example.book.service.AuthorServiceImpl;
import com.example.exception_handler.LMSServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void createAuthor_success() {
        Author author = new Author();
        author.setName("John Doe");

        when(authorRepository.existsByName("John Doe")).thenReturn(false);
        when(authorRepository.save(author)).thenReturn(author);

        Author saved = authorService.createAuthor(author);
        assertNotNull(saved);
        assertEquals("John Doe", saved.getName());
        verify(authorRepository).save(author);
    }

    @Test
    void createAuthor_alreadyExists_throwsException() {
        Author author = new Author();
        author.setName("John Doe");

        when(authorRepository.existsByName("John Doe")).thenReturn(true);

        LMSServiceException ex = assertThrows(LMSServiceException.class, () -> authorService.createAuthor(author));
        assertEquals("Author with this name already exists", ex.getMessage());
    }

    @Test
    void getAllAuthors_success() {
        Author a1 = new Author(); a1.setName("A1");
        Author a2 = new Author(); a2.setName("A2");

        when(authorRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Author> authors = authorService.getAllAuthors();
        assertEquals(2, authors.size());
    }
}
