package lab2;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TariffSAXParserTest {
    private Parseable<Tariff> helper = new MyParseHelper();
    private TariffSAXParser<Tariff> parser;

    @Before
    public void setUp() {
        try {
            parser = new TariffSAXParser<>("G:\\OOP1\\Lab2_XMLParser\\TestFile.xml",
                    "", helper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllTariffsEmptyTree() {
        System.out.println("getAllTariffs");
        assertEquals(false, parser.getAllTariffs().iterator().hasNext());
        assertEquals(0, parser.getAllTariffs().size());
    }

    @Test
    public void testGetAllTariffsNotEmptyTree() {
        System.out.println("getAllTariffs");
        try {
            parser.parse();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        assertNotEquals(null, parser.getAllTariffs());
        assertEquals(1, parser.getAllTariffs().size());
    }


    @Test
    public void testParse() throws Exception {
        System.out.println("parse");
        parser.parse();
        Tariff tariff = parser.getAllTariffs().first();
        double eps = 0.001;
        assertEquals(tariff.getId(), "ID-1");
        assertEquals(tariff.getName(), "Kyivstar Comfort");
        assertEquals(tariff.getOperatorName(), Operator.KYIVSTAR);
        assertEquals(tariff.getPayroll(), 75, eps);
        assertEquals(tariff.getInsideNetworkPrice().getCurrency(), "hrn");
        assertEquals(tariff.getOutsideNetworkPrice().getPrice(), 1, eps);
        assertEquals(tariff.getLandlinePrice().getTariffAmount(), 1);
        assertEquals(tariff.isSelectedNumberPresence(), true);
        assertEquals(tariff.getTariffication(), "minute");
        assertEquals(tariff.getTariffConectionPrice(), 75, eps);
    }
}
