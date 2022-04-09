package com.xmlproject.service;

import com.xmlproject.model.Document;
import com.xmlproject.repo.RepoImpl;
import com.xmlproject.repo.Repository;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;

public class ServiceImpl implements Service {
    Repository repo = new RepoImpl();
    ReadXmlStAXCursorParser readxml = new ReadXmlStAXCursorParser();

    @Override
    public void add(Document document) {
        repo.add(document);
    }

    @Override
    public List<Document> getAll() {
        return repo.getAll();
    }

    @Override
    public HashSet<String> getAllWords(String path) throws XMLStreamException, FileNotFoundException {
        return readxml.getWords(path);
    }

    @Override
    public int getNumberOfLines(String path) throws XMLStreamException, FileNotFoundException {
        return readxml.getNumberOfLines(path);
    }
}
