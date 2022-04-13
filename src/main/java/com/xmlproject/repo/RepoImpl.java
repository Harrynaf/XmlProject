package com.xmlproject.repo;

import com.xmlproject.model.Book;

import java.util.ArrayList;
import java.util.List;

public class RepoImpl implements Repository {

    List<Book> documentList = new ArrayList<>();

    @Override
    public void add(Book document) {
        documentList.add(document);
    }

    @Override
    public List<Book> getAll() {
        return documentList;
    }
}
