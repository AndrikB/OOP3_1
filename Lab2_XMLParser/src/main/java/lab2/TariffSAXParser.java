/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;



public class TariffSAXParser<T extends Comparable<T>> extends EventBasedParser<T> {

    private final SAXParserFactory factory;
    private final SAXParser parser;

    public TariffSAXParser(String xmlFile, String xsdFile, Parseable<T> parseHelper)
            throws ParserConfigurationException, SAXException, IOException {

        super(xmlFile, xsdFile, parseHelper);
        //get SAX analyzer
        factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
    }

    @Override
    public void parse() throws ParserConfigurationException, SAXException, IOException {
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(xmlFile), handler);

    }

    @Override
    public TreeSet<T> getAllTariffs() {
        return tariffs;
    }

    private class XMLHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {

            elemStart(uri, localName, qName);
            for (int i = 0; i < attributes.getLength(); ++i) {
                tariff = initializeFieldWithAttribute(tagName, attributes.getQName(i),
                        attributes.getValue(i), tariff);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            elemEnd(uri, localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            charElement(ch, start, length);
        }
    }
}
