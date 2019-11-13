package lab2;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        String xml = "G:\\OOP1\\Lab2_XMLParser\\Tariffs.xml";
        String xsd = "G:\\OOP1\\Lab2_XMLParser\\Tariffs.xsd";
        try {
            MyParseHelper helper = new MyParseHelper();
            AbstractParser<Tariff> pars
                    = new DOMParser<>(xml, xsd, helper);
            pars.parse();
            TreeSet<Tariff> tariffs = pars.getAllTariffs();
            System.out.println(tariffs);
            System.out.println(pars.isValid(xml, xsd));
        } catch (ParserConfigurationException ex) {
            System.out.println("exception");
        } catch (XMLStreamException ex) {
            System.out.println("XMLStream exception");
        } catch (SAXException ex) {
            System.out.println("SAXException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }

    }

}
