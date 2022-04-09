package com.xmlproject.repo;

import com.xmlproject.model.Document;

import java.util.List;

public interface Repository {
    void add(Document document);
    List<Document> getAll();
}
