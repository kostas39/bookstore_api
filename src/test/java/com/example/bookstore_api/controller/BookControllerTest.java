package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        Book book = new Book(1L, "JUnit 5 Guide", "John Tester");
        String response = bookController.addBook(book);

        assertEquals("Book added successfully!", response);
        assertTrue(bookController.getAllBooks().containsKey(1L));
    }

    @Test
    void testGetAllBooks() {
        bookController.addBook(new Book(1L, "Spring Boot Testing", "Jane Doe"));
        bookController.addBook(new Book(2L, "Mockito for Beginners", "Alex Smith"));

        Map<Long, Book> books = bookController.getAllBooks();

        assertEquals(2, books.size());
    }

    @Test
    void testGetBookById() {
        Book book = new Book(1L, "Test-Driven Development", "Kent Beck");
        bookController.addBook(book);

        Book foundBook = bookController.getBookById(1L);
        assertNotNull(foundBook);
        assertEquals("Test-Driven Development", foundBook.getTitle());
    }

    @Test
    void testDeleteBook() {
        bookController.addBook(new Book(1L, "Delete Me", "Author"));
        String response = bookController.deleteBook(1L);

        assertEquals("Book deleted successfully!", response);
        assertFalse(bookController.getAllBooks().containsKey(1L));
    }
}
