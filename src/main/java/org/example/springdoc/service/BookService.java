package org.example.springdoc.service;


import org.example.springdoc.domain.Book;

import java.util.List;

public interface BookService {
    Book findById(long id);
    List<Book> findBooks();
}
