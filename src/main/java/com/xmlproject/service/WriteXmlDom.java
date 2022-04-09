package com.xmlproject.service;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class WriteXmlDom {
    public static void run() {
        try {
            BufferedReader reader;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("document");
            rootElement.setAttribute("xmlns","https://www.w3schools.com");
            rootElement.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xsi:schemaLocation","https://www.w3schools.com/xml lorem.xsd");
            doc.appendChild(rootElement);

            try {
                reader = new BufferedReader(new FileReader(
                        "xml_files/lorem.txt"));
                String line = reader.readLine();
                Integer index = 1;
                String list[];
                while (line != null) {
                    list=null;
                    if (!line.isEmpty()) {
                        Element paragraphElement = doc.createElement("paragraph");
                        paragraphElement.setAttribute("id", index.toString());
                        rootElement.appendChild(paragraphElement);
                        list = line.split("\\.");
                        for (String s : list) {
                            Element lineElement = doc.createElement("line");
                            lineElement.setTextContent(s);
                            paragraphElement.appendChild(lineElement);
                        }
                        index++;
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DOMException e) {
                e.printStackTrace();
            }

            //...create XML elements, and others...

            // write dom document to a file
            try (FileOutputStream output = new FileOutputStream("xml_files/lorem.xml")) {
                writeXml(doc, output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    // write doc to output stream
    private static void writeXml(Document doc, OutputStream output)
            throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        transformer.transform(source, result);
    }
}