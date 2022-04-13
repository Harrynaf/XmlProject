package com.xmlproject.cases;

import com.xmlproject.model.Book;
import com.xmlproject.service.*;
import jakarta.xml.bind.JAXBException;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UseCase {
    static Service service = new ServiceImpl();

    public void parsingValidationCase() {
        //Test
        System.out.println("----------------------------------Test Validation/Write-------------------------------------------");
        try {
            System.out.println("Parsing to xml was succesufull? " + new WriteXml().WriteXmlDomRun());

            /* writeXmlStAX is much slower*/
            // new WriteXml().writeXmlStAXRun("xml_files/lorem.xml");

            System.out.println("Validation was succesufull? " + new ValidateXSD().validateXMLSchema("xml_files/lorem.xsd", "xml_files/lorem.xml"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readAndWriteCase() throws JAXBException {
        // Test Service
        System.out.println("----------------------------------Test Service marshalling-------------------------------------------");
        System.out.println("(Writing) Marshalling was succesufull? " + new Marshal().marshallingTest("xml_files/Marshalledlorem.xml"));
        System.out.println("----------------------------------Test Service add by using unmarshalling-------------------------------------------");
        service.add(new Marshal().unmarshalling("xml_files/lorem2.xml"));
        System.out.println("(Reading) Added new: " + service.getAll().get(0));
        service.add(new Marshal().unmarshalling("xml_files/lorem3.xml"));
        System.out.println("(Reading) Added new: " + service.getAll().get(1));
        /*Need to add ns2: to book element in xml since marshaller adds it to book element, tried to resolve this by adding xmlns = @XmlNs(prefix = "" to package-info.java but to no avail */
    }

    public void getStatisticsCase() throws JAXBException {
        System.out.println("----------------------------------Test Service getStats Statistics-------------------------------------------");
        System.out.println(service.getStats(new Marshal().unmarshalling("xml_files/lorem2.xml")));
    }

    public void xmlByStealingParagraphsCase() throws JAXBException {
        System.out.println("----------------------------------Creating XML with selected paragraphs from an existing XML-------------------------------------------");
        //Steal some paragraphs
        List<Book.Chapter.Paragraph> paragraphList = new ArrayList<>();
        paragraphList.addAll(new Marshal().unmarshalling("xml_files/lorem3.xml").getChapter().get(0).getParagraph());
        //Add them to new Book and print it/or marshall it to xml
        System.out.println("(Writing) Output file MarshalledByStealingParagraphslorem.xml was succesufull? " + new Marshal().marshallingByParagraph("xml_files/MarshalledByStealingParagraphslorem.xml", paragraphList));
    }

    public void getAllCase() throws XMLStreamException, FileNotFoundException {
        /*My cases*/
        System.out.println("----------------------------------Test Service getAll-------------------------------------------");
        for (Book b : service.getAll())
            System.out.println(b);
        System.out.println("----------------------------------Test Service getAllWords-------------------------------------------");
        System.out.println(service.getAllWords("xml_files/lorem2.xml"));
        System.out.println("Number of distinct words: " + service.getAllWords("xml_files/lorem2.xml").size());
        System.out.println("----------------------------------Test Service getNumberOfLines-------------------------------------------");
        System.out.println("All lines: " + service.getNumberOfLines("xml_files/lorem2.xml"));
    }
}