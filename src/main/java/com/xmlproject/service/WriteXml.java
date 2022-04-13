package com.xmlproject.service;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
public class WriteXml {
    public boolean WriteXmlDomRun() {
        try {
            BufferedReader reader;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("book");
            rootElement.setAttribute("xmlns", "https://www.w3schools.com");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xsi:schemaLocation", "https://www.w3schools.com/xml lorem.xsd");
            doc.appendChild(rootElement);

            Element statisticsElement = doc.createElement("statistics");
            rootElement.appendChild(statisticsElement);

            Integer chapterIndex = 1;
            Integer paragraphIndex = 1;
            Integer lineIndex = null;
            Integer paragraphCount = 1;
            Integer lineCount = 0;
            HashSet<String> words = new HashSet();
            List<String> allWords = new ArrayList<>();

            try {
                reader = new BufferedReader(new FileReader(
                        "xml_files/lorem.txt"));
                String line = reader.readLine();
                String list[];

                Element chapterElement = doc.createElement("chapter");
                chapterElement.setAttribute("id", chapterIndex.toString());
                rootElement.appendChild(chapterElement);


                while (line != null) {

                    if (paragraphIndex == 500) {
                        chapterIndex++;
                        paragraphIndex=1;
                        chapterElement = doc.createElement("chapter");
                        chapterElement.setAttribute("id", chapterIndex.toString());
                        rootElement.appendChild(chapterElement);
                    }

                    list = null;
                    if (!line.isEmpty()) {
                        Element paragraphElement = doc.createElement("paragraph");
                        paragraphElement.setAttribute("id", paragraphIndex.toString());
                        chapterElement.appendChild(paragraphElement);
                        list = line.split("\\.");
                        lineIndex = 1;
                        for (String s : list) {
                            Element lineElement = doc.createElement("line");
                            lineElement.setTextContent(s);
                            lineIndex++;
                            lineCount++;
                            paragraphElement.appendChild(lineElement);
                            String[] wordsInLine = s.split("[\\W\\.\\,]");
                            words.addAll(List.of(wordsInLine));
                            allWords.addAll(List.of(wordsInLine));
                        }
                        paragraphIndex++;
                        paragraphCount++;
                    }


                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException |DOMException e) {
                log.error(e.getMessage());
                return false;
            }

            Element dateTimeOfCreationElement = doc.createElement("dateTimeOfCreation");
            dateTimeOfCreationElement.setTextContent(LocalDateTime.now().toString());
            statisticsElement.appendChild(dateTimeOfCreationElement);
            Element authorElement = doc.createElement("author");
            authorElement.setTextContent("Tolkien");
            statisticsElement.appendChild(authorElement);
            Element applicationClassNameElement = doc.createElement("applicationClassName");
            applicationClassNameElement.setTextContent(WriteXml.class.getSimpleName());
            statisticsElement.appendChild(applicationClassNameElement);
            Element statisticsChaptersElement = doc.createElement("chapters");
            statisticsChaptersElement.setTextContent(chapterIndex.toString());
            statisticsElement.appendChild(statisticsChaptersElement);
            Element statisticsParagraphsElement = doc.createElement("paragraphs");
            statisticsParagraphsElement.setTextContent(paragraphCount.toString());
            statisticsElement.appendChild(statisticsParagraphsElement);
            Element statisticsLinesElement = doc.createElement("lines");
            statisticsLinesElement.setTextContent(lineCount.toString());
            statisticsElement.appendChild(statisticsLinesElement);
            Element statisticsDistinctWordsElement = doc.createElement("distinctWords");
            Integer wordsInt= words.size();
            statisticsDistinctWordsElement.setTextContent(wordsInt.toString());
            statisticsElement.appendChild(statisticsDistinctWordsElement);
            Integer allWordsInt= allWords.size();
            Element statisticsAllWordsElement = doc.createElement("allWords");
            statisticsAllWordsElement.setTextContent(allWordsInt.toString());
            statisticsElement.appendChild(statisticsAllWordsElement);



            // write dom document to a file
            try (FileOutputStream output = new FileOutputStream("xml_files/lorem.xml")) {
                writeXml(doc, output);
            } catch (IOException e) {
                log.error(e.getMessage());
                return false;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    private static void writeXmlStAX(OutputStream out) throws XMLStreamException, ParserConfigurationException, TransformerConfigurationException {

        BufferedReader reader;


        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(out);


        writer.writeStartElement("book");
        writer.writeAttribute("xmlns", "https://www.w3schools.com");
        writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        writer.writeAttribute("xsi:schemaLocation", "https://www.w3schools.com/xml lorem.xsd");


        try {
            reader = new BufferedReader(new FileReader(
                    "xml_files/lorem.txt"));
            String line = reader.readLine();
            Integer index = 1;
            String list[];
            while (line != null) {
                list = null;
                if (!line.isEmpty()) {
                    writer.writeStartElement("paragraph");
                    writer.writeAttribute("id", index.toString());
                    list = line.split("\\.");
                    for (String s : list) {
                        writer.writeStartElement("line");
                        writer.writeCharacters(s);
                        writer.writeEndElement();
                    }
                    index++;
                    writer.writeEndElement();
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            log.error(e.getMessage());


            writer.writeEndElement();

            writer.writeEndDocument();
            // </company>

            writer.flush();
            writer.close();
        }


    }

    // write doc to output stream
    public static void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);
    }

    public void writeXmlStAXRun(String file) throws XMLStreamException {
        // send the output to a xml file
        try (FileOutputStream out = new FileOutputStream(file)) {
            writeXmlStAX(out);
        } catch (IOException | ParserConfigurationException | TransformerConfigurationException e) {
            log.error(e.getMessage());
        }
        // send the output to System.out
        // writeXml(System.out);
    }
}