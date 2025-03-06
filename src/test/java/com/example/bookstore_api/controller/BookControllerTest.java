package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Book;
import com.example.bookstore_api.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book(1L, "JUnit 5 Guide", "John Tester");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        String response = bookController.addBook(book);

        assertEquals("Book added successfully!", response);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(
                new Book(1L, "Spring Boot Testing", "Jane Doe"),
                new Book(2L, "Mockito for Beginners", "Alex Smith")
        ));

        List<Book> books = bookController.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    void testGetBookById() {
        Book book = new Book(1L, "Test-Driven Development", "Kent Beck");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book foundBook = bookController.getBookById(1L);
        assertNotNull(foundBook);
        assertEquals("Test-Driven Development", foundBook.getTitle());
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        String response = bookController.deleteBook(1L);
        assertEquals("Book deleted successfully!", response);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
