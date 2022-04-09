package com.xmlproject.repo;

import com.xmlproject.model.Document;

import java.util.ArrayList;
import java.util.List;

public class RepoImpl implements Repository {

    List<Document> documentList = new ArrayList<>();

    @Override
    public void add(Document document) {
        documentList.add(document);
    }

    @Override
    public List<Document> getAll() {
        return documentList;
    }
}
