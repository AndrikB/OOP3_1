/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import org.xml.sax.SAXException;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;


public abstract class AbstractParser<T extends Comparable<T>> {

    protected final String xmlFile;
    protected final String xsdFile;
    protected TreeSet<T> tariffs = new TreeSet<>();
    protected T tariff;
    private final Parseable<T> parseHelper;

    public abstract TreeSet<T> getAllTariffs();

    public abstract void parse()
            throws ParserConfigurationException, SAXException, IOException, XMLStreamException;

    public AbstractParser(String xmlFile, String xsdFile, Parseable<T> parseHelper) {
        this.xmlFile = xmlFile;
        this.xsdFile = xsdFile;
        this.parseHelper = parseHelper;
        this.tariff = parseHelper.getClassItem();
        tariffs = new TreeSet<>(/*Comparator.comparing(Tariff::getOperatorName).
                thenComparing(Tariff::getName).thenComparing(Tariff::getId)*/);
    }

    public boolean isValid(String xmlFile, String xsdFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(xsdFile));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));
            return true;
        } catch (SAXException | IOException e) {
            return false;
        }
    }

    protected T initializeFieldWithTag(String tagName, String text, T classItem) {
        return parseHelper.initializeFieldWithTag(tagName, text, classItem);
    }

    protected T initializeFieldWithAttribute(String tagName, String attrName,
                                             String text, T classItem) {
        return parseHelper.initializeFieldWithAttribute(tagName, attrName, text, classItem);
    }

    protected String getRootElementName() {
        return parseHelper.getRootElementName();
    }

    protected T getNewClassItem() {
        return parseHelper.getClassItem();
    }

    public T getClassItem() {
        return tariff;
    }
}
