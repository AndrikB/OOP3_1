/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;
import org.w3c.dom.Element;


public class DOMParser<T extends Comparable<T>> extends AbstractParser<T> {

    private final DocumentBuilderFactory factory;
    private final DocumentBuilder builder;
    private Document document;
    private Element root;

    public DOMParser(String xmlFile, String xsdFile, Parseable<T> parseHelper) throws ParserConfigurationException {
        super(xmlFile, xsdFile, parseHelper);
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();

    }

    @Override
    public TreeSet<T> getAllTariffs() {
        return tariffs;
    }

    @Override
    public void parse() throws ParserConfigurationException, SAXException, IOException {

        document = builder.parse(new File(xmlFile));
        root = document.getDocumentElement();

        for (Node childNode = root; childNode != null; childNode = childNode.getNextSibling()) {
            recursiveParse(childNode);
        }
        if (tariff != null) {
            //add item to the collection
            tariffs.add(tariff);
        }
    }

    public void recursiveParse(Node start)
            throws ParserConfigurationException, SAXException, IOException {
        if (start == null) {
            return;
        }
        for (Node childNode = start; childNode != null;
             childNode = childNode.getNextSibling()) {

            if (childNode.getNodeType() == Node.ELEMENT_NODE
                    && childNode.getNodeName().equals(getRootElementName())) {
                if (tariff != null) {
                    //add item to collection
                    tariffs.add(tariff);
                }

                //create new item
                tariff = getNewClassItem();
            }

            if (childNode.getNodeType() == Node.TEXT_NODE
                    && childNode.getNodeValue() != null && !childNode.getNodeValue().contains("\n")) {
                tariff = initializeFieldWithTag(childNode.getParentNode().getNodeName(),
                        childNode.getNodeValue(), tariff);
            }

            if (childNode.getAttributes() != null) {
                for (int j = 0; j < childNode.getAttributes().getLength(); ++j) {
                    tariff = initializeFieldWithAttribute(childNode.getNodeName(),
                            childNode.getAttributes().item(j).getNodeName(),
                            childNode.getAttributes().item(j).getNodeValue(), tariff);
                }
            }
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                recursiveParse(childNode.getFirstChild());
            }
        }
    }
}
