package com.xmlproject.service;

import com.xmlproject.model.Book;
import jakarta.xml.bind.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Marshal {

    public boolean marshallingTest(String path) {
        // the environment - JAXB entry point
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Book.class);
        } catch (JAXBException e) {

            log.error(e.getMessage());
            return false;
        }
        Marshaller marshaller = null;
        try {
            marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            log.error(e.getMessage());
            return false;
        }
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //pretty print XML
        } catch (PropertyException e) {
            log.error(e.getMessage());
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
        List<Book.Chapter.Paragraph> paragraphList = new ArrayList<>();
        paragraphList.add(new Book.Chapter.Paragraph(line, BigInteger.ONE));
        paragraphList.add(new Book.Chapter.Paragraph(line1, BigInteger.TWO));

        Book.Statistics statistics =new Book.Statistics();
        Book.Chapter chapter = new Book.Chapter(paragraphList,BigInteger.ONE);
        List<Book.Chapter> chapterList= new ArrayList<>();
        chapterList.add(chapter);

        // to screen
        // marshaller.marshal(new Document(paragraphList), System.out);

        // to string
        StringWriter DocumentXml = new StringWriter();
        try {
            marshaller.marshal(new Book(statistics,chapterList), DocumentXml);
        } catch (JAXBException e) {
            log.error(e.getMessage());
            return false;
        }
        // System.out.println(DocumentXml);

        // to file
        try {
            marshaller.marshal(new Book(statistics,chapterList), new File(path));
        } catch (JAXBException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public Book unmarshalling(String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        // System.out.println(document);
        return (Book) unmarshaller.unmarshal(new File(path));
    }

    public boolean marshallingByParagraph(String path,List<Book.Chapter.Paragraph> paragraphList) {
        // the environment - JAXB entry point
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Book.class);
        } catch (JAXBException e) {

            log.error(e.getMessage());
            return false;
        }
        Marshaller marshaller = null;
        try {
            marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            log.error(e.getMessage());
            return false;
        }
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //pretty print XML
        } catch (PropertyException e) {
            log.error(e.getMessage());
            return false;
        }


        Book.Statistics statistics =new Book.Statistics();
        Book.Chapter chapter = new Book.Chapter(paragraphList,BigInteger.ONE);
        List<Book.Chapter> chapterList= new ArrayList<>();
        chapterList.add(chapter);

        // to screen
        // marshaller.marshal(new Document(paragraphList), System.out);

        // to string
        StringWriter DocumentXml = new StringWriter();
        try {
            marshaller.marshal(new Book(statistics,chapterList), DocumentXml);
        } catch (JAXBException e) {
            log.error(e.getMessage());
            return false;
        }
        // System.out.println(DocumentXml);

        // to file
        try {
            marshaller.marshal(new Book(statistics,chapterList), new File(path));
        } catch (JAXBException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
