package lab2;

import java.io.IOException;
import java.util.TreeSet;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

public class AbstractParserTest {
    private MyParseHelper helper = new MyParseHelper();

    @Test
    public void testIsValid() {
        System.out.println("isValid");
        String xml = "G:\\OOP1\\Lab2_XMLParser\\Tariffs.xml";
        String xsd = "G:\\OOP1\\Lab2_XMLParser\\Tariffs.xsd";
        String xml2 = "G:\\OOP1\\Lab2_XMLParser\\NotValidXML.xml";
        AbstractParser instance = new AbstractParserImpl();
        assertEquals(true, instance.isValid(xml, xsd));
        assertEquals(false, instance.isValid(xml2, xsd));
    }

    public class AbstractParserImpl extends AbstractParser<Tariff> {

        public AbstractParserImpl() {
            super("", "", new MyParseHelper());
        }

        public TreeSet<Tariff> getAllTariffs() {
            return null;
        }

        public void parse() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        }
    }

}
