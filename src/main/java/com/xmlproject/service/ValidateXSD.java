package com.xmlproject.service;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidateXSD {
    public boolean validateXMLSchema(String xsdFilePath, String xmlFilePath) {
        File xsdFile = new File(xsdFilePath);
        File xmlFile = new File(xmlFilePath);

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (IOException | IllegalArgumentException | SAXException e) {
            //System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
