package lab2;


public abstract class EventBasedParser<T extends Comparable<T>> extends AbstractParser<T> {

    protected String tagName;

    public EventBasedParser(String xmlFile, String xsdFile, Parseable<T> parseHelper) {
        super(xmlFile, xsdFile, parseHelper);
    }

    public void elemStart(String uri, String localName, String qName) {
        tagName = qName;
        if (qName.equals(getRootElementName())) {
            //create new item
            tariff = getNewClassItem();
        }
    }

    public void elemEnd(String uri, String localName, String qName) {
        if (qName.equals(getRootElementName())) {
            //add item to the collection
            tariffs.add(tariff);
        }
    }

    public void charElement(char[] ch, int start, int length) {
        String text = new String(ch, start, length);
        text = text.replace("\n", "").trim();
        if (!text.isEmpty()) {
            tariff = initializeFieldWithTag(tagName, text, tariff);
        }
    }
}
