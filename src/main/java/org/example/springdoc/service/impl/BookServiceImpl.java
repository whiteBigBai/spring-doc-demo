package org.example.springdoc.service.impl;

import org.example.springdoc.domain.Book;
import org.example.springdoc.mapper.BookMapper;
import org.example.springdoc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public Book findById(long id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> findBooks() {
        return bookMapper.selectList(null);
    }
}
