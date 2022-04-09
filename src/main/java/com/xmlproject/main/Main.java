package com.xmlproject.main;

import com.xmlproject.model.Document;
import com.xmlproject.service.*;
import jakarta.xml.bind.JAXBException;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws JAXBException, XMLStreamException, FileNotFoundException {
        //Test
        System.out.println("----------------------------------Test Validation/Write-------------------------------------------");
        try {
            new WriteXmlDom();
            WriteXmlDom.run();
            System.out.println("Validation was succesufull? "+new ValidateXSD().validateXMLSchema("xml_files/lorem.xsd", "xml_files/lorem.xml"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Test Service
        System.out.println("----------------------------------Test Service marshalling-------------------------------------------");
        Service service = new ServiceImpl();
        System.out.println("Marshalling was succesufull? "+new Marshal().marshalling("xml_files/Document2.xml"));
        System.out.println("----------------------------------Test Service add by using unmarshalling-------------------------------------------");
        service.add(new Marshal().unmarshalling("xml_files/lorem1.xml"));
        System.out.println("Added new: "+service.getAll().get(0));
        service.add(new Marshal().unmarshalling("xml_files/Document2.xml"));
        System.out.println("Added new: "+service.getAll().get(1));
        System.out.println();
        System.out.println("----------------------------------Test Service getAll-------------------------------------------");
        for (Document d : service.getAll())
            System.out.println(d);
        System.out.println("----------------------------------Test Service getAllWords-------------------------------------------");
        System.out.println(service.getAllWords("xml_files/lorem.xml"));
        System.out.println("Number of distinct words: "+service.getAllWords("xml_files/lorem.xml").size());
        System.out.println("----------------------------------Test Service getNumberOfLines-------------------------------------------");
        System.out.println("All lines: "+service.getNumberOfLines("xml_files/lorem1.xml"));
    }
}
