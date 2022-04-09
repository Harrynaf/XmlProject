package com.xmlproject.service;

import com.xmlproject.model.Document;
import jakarta.xml.bind.*;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Marshal {

    public boolean marshalling(String path) {
        // the environment - JAXB entry point
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Document.class);
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
        Marshaller marshaller = null;
        try {
            marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //pretty print XML
        } catch (PropertyException e) {
            e.printStackTrace();
            return false;
        }

        List<String> line = new ArrayList<>();
        line.add("dasdas");
        line.add("safdfas");
        line.add("asfasdgad");
        line.add("jdgjdfgjfdjg");
        List<String> line1 = new ArrayList<>();
        line1.add("123123412");
        line1.add("43575687564674");
        line1.add("345634564356");
        line1.add("56754672457457");
        List<Document.Paragraph> paragraphList = new ArrayList<>();
        paragraphList.add(new Document.Paragraph(line, 1));
        paragraphList.add(new Document.Paragraph(line1, 2));
        // to screen
        // marshaller.marshal(new Document(paragraphList), System.out);

        // to string
        StringWriter DocumentXml = new StringWriter();
        try {
            marshaller.marshal(new Document(paragraphList), DocumentXml);
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
        // System.out.println(DocumentXml);

        // to file
        try {
            marshaller.marshal(new Document(paragraphList), new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Document unmarshalling(String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        // System.out.println(document);
        return (Document) unmarshaller.unmarshal(new File(path));
    }
}
