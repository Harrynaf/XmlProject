package com.xmlproject.repo;

import com.xmlproject.model.Book;

import java.util.List;

public interface Repository {
    void add(Book document);
    List<Book> getAll();
}
