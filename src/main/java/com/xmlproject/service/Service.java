package com.xmlproject.service;

import com.xmlproject.model.Book;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.List;

public interface Service {
    void add(Book document);

    List<Book> getAll();

    /**
     * @param path
     * @return Returns all distinct words in an xml file.
     * @throws XMLStreamException
     * @throws FileNotFoundException
     */
    List<String> getAllWords(String path) throws XMLStreamException, FileNotFoundException;

    /**
     * @param path
     * @return Returns the sum of all lines in an xml file.
     * @throws XMLStreamException
     * @throws FileNotFoundException
     */
    int getNumberOfLines(String path) throws XMLStreamException, FileNotFoundException;

    /**
     * @param document
     * @return Returns the statistics of a book object or a xml file (passing it with unmarshalling).
     */
    String getStats(Book document);
}
