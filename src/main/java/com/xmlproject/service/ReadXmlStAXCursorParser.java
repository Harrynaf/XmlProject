package com.xmlproject.service;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;


//StAX Cursor API to read a XML file

public class ReadXmlStAXCursorParser {

    public int getNumberOfLines(String path)
            throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(
                new FileInputStream(Paths.get(path).toFile()));

        int eventType = reader.getEventType();
//        System.out.println(eventType);   // 7, START_DOCUMENT
//        System.out.println(reader);      // xerces

        int linecount = 0;
        int paragraphcount = 0;
        while (reader.hasNext()) {

            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {

                switch (reader.getName().getLocalPart()) {

                    case "line":
                        eventType = reader.next();
                        linecount++;
                        break;

                    case "paragraph":
                        eventType = reader.next();
                        paragraphcount++;
                        break;
                }

            }

        }
        return linecount;
    }

    public HashSet<String> getWords(String path)
            throws FileNotFoundException, XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(
                new FileInputStream(Paths.get(path).toFile()));

        int eventType = reader.getEventType();
//        System.out.println(eventType);   // 7, START_DOCUMENT
//        System.out.println(reader);      // xerces

        HashSet<String> words = new HashSet();
        while (reader.hasNext()) {

            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {

                if ("line".equals(reader.getName().getLocalPart())) {
                    eventType = reader.next();
                    if (eventType == XMLEvent.CHARACTERS) {
                        String[] wordsInLine = reader.getText().split("\\s+");
                        words.addAll(List.of(wordsInLine));
                    }
                }

            }

        }
        return words;
    }

}