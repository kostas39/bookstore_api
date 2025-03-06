package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final Map<Long, Book> bookMap = new HashMap<>();

    @GetMapping
    public Map<Long, Book> getAllBooks() {
        return bookMap;
    }

    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookMap.put(book.getId(), book);
        return "Book added successfully!";
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookMap.get(id);
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        if (bookMap.containsKey(id)) {
            bookMap.put(id, updatedBook);
            return "Book updated successfully!";
        }
        return "Book not found!";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        if (bookMap.containsKey(id)) {
            bookMap.remove(id);
            return "Book deleted successfully!";
        }
        return "Book not found!";
    }
}

