package lab2;

import java.io.IOException;
import java.util.TreeSet;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

public class EventBasedParserTest {

    private Parseable<Tariff> helper = new MyParseHelper();
    private DOMParser<Tariff> parser;
    private EventBasedParser instance;

    @Before
    public void setUp() {
        try {
            parser = new DOMParser<>("G:\\OOP1\\Lab2_XMLParser\\TestFile.xml",
                    "", new MyParseHelper());
            instance = new EventBasedParserImpl();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testElemStart() {
        System.out.println("docStart");
        instance.elemStart("", "", TagNames.TARIFF);
        assertEquals(instance.getClassItem().getClass().getName(), "lab2.Tariff");
    }

    @Test
    public void testElemEnd() {
        System.out.println("elemEnd");
        instance.elemStart("", "", "");
        instance.elemEnd("", "", "myName");
        assertEquals(instance.getAllTariffs().size(), 0);
        instance.elemEnd("", "", TagNames.TARIFF);
        assertEquals(instance.getAllTariffs().size(), 1);
        assertEquals(instance.getAllTariffs().first().getClass().getName(), "lab2.Tariff");
    }

    @Test
    public void testcharElement() {
        System.out.println("charElem");
        instance.elemStart("", "", TagNames.NAME);
        char[] name = "myName".toCharArray();
        instance.charElement(name, 1, 0);
        assertEquals(((Tariff)instance.getClassItem()).getName(), null);
        instance.charElement(name, 1, name.length-1);
        assertEquals(((Tariff)instance.getClassItem()).getName(), "yName");
    }

    public class EventBasedParserImpl extends EventBasedParser {

        public EventBasedParserImpl() {
            super("", "", new MyParseHelper());
            tariffs = new TreeSet();
        }

        @Override
        public TreeSet getAllTariffs() {
            return tariffs;
        }

        @Override
        public void parse() throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}