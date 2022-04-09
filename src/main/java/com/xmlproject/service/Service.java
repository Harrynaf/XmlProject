package com.xmlproject.service;

import com.xmlproject.model.Document;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;

public interface Service {
    void add(Document document);
    List<Document> getAll();
    HashSet<String> getAllWords(String path) throws XMLStreamException, FileNotFoundException;
    int getNumberOfLines(String path) throws XMLStreamException, FileNotFoundException;
}
