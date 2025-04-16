package org.example.springdoc.controller;

import org.example.springdoc.domain.Book;
import org.example.springdoc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public Book findById(@PathVariable long id) {
        return bookService.findById(id);
    }

    @GetMapping("/")
    public List<Book> findBooks() {
        return bookService.findBooks();
    }
}
