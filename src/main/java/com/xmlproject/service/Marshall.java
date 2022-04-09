package com.xmlproject.service;

import com.xmlproject.model.Document;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Marshall {

    public void marshalling(String path) throws JAXBException {
        // the environment - JAXB entry point
        JAXBContext context = JAXBContext.newInstance(Document.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //pretty print XML

        List<String> line = new ArrayList<String>();
        line.add("dasdas");
        line.add("safdfas");
        line.add("asfasdgad");
        line.add("jdgjdfgjfdjg");
        List<String> line1 = new ArrayList<String>();
        line1.add("123123412");
        line1.add("43575687564674");
        line1.add("345634564356");
        line1.add("56754672457457");
        List<Document.Paragraph> paragraphList = new ArrayList<>();
        paragraphList.add(new Document.Paragraph(line,1));
        paragraphList.add(new Document.Paragraph(line1,2));
        // to screen
        marshaller.marshal(new Document(paragraphList), System.out);

        // to string
        StringWriter DocumentXml = new StringWriter();
        marshaller.marshal(new Document(paragraphList), DocumentXml);
        System.out.println(DocumentXml);
        
        // to file
        marshaller.marshal(new Document(paragraphList), new File(path));
        
    }

    public Document unmarshalling(String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Document document = (Document)unmarshaller.unmarshal(new File(path));
        System.out.println(document);
        return document;
    }
}
