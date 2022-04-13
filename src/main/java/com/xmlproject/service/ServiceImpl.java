package com.xmlproject.service;

import com.xmlproject.model.Book;
import com.xmlproject.repo.RepoImpl;
import com.xmlproject.repo.Repository;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;

public class ServiceImpl implements Service {
    Repository repo = new RepoImpl();
    ReadXmlStAXCursorParser readxml = new ReadXmlStAXCursorParser();

    @Override
    public void add(Book document) {
        repo.add(document);
    }


    @Override
    public List<Book> getAll() {
        return repo.getAll();
    }


    @Override
    public List<String> getAllWords(String path) throws XMLStreamException, FileNotFoundException {
        return readxml.getWords(path);
    }


    @Override
    public int getNumberOfLines(String path) throws XMLStreamException, FileNotFoundException {
        return readxml.getNumberOfLines(path);
    }


    @Override
    public String getStats(Book document) {
        return document.getStatistics().toString();
    }
}
