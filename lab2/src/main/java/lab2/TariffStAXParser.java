/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.xml.sax.SAXException;


public class TariffStAXParser<T extends Comparable<T>> extends EventBasedParser<T> {

    private final XMLInputFactory factory;
    private final XMLStreamReader reader;

    public TariffStAXParser(String xmlFile, String xsdFile, Parseable<T> parseHelper)
            throws FileNotFoundException, XMLStreamException, MalformedURLException, IOException {
        super(xmlFile, xsdFile, parseHelper);
        factory = XMLInputFactory.newInstance();
        File file = new File(xmlFile);
        reader = factory.createXMLStreamReader(new FileReader(file));

    }

    @Override
    public TreeSet<T> getAllTariffs() {
        return tariffs;
    }

    @Override
    public void parse()
            throws ParserConfigurationException, SAXException, IOException, XMLStreamException {

        for (int event = reader.getEventType(); reader.hasNext(); event = reader.next()) {
            switch (event) {

                case XMLStreamConstants.START_ELEMENT:
                    elemStart(reader.getNamespaceURI(), reader.getLocalName(),
                            reader.getName().toString());
                    for (int i = 0; i < reader.getAttributeCount(); ++i) {
                        tariff = initializeFieldWithAttribute(tagName, reader.getAttributeName(i).toString(),
                                reader.getAttributeValue(i), tariff);
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    charElement(reader.getTextCharacters(), reader.getTextStart(),
                            reader.getTextLength());
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elemEnd(reader.getNamespaceURI(), reader.getLocalName(),
                            reader.getName().toString());
                    break;
            }

        }
    }

}
