package lab2;


public interface Parseable<T> {
    T initializeFieldWithTag(String tagName, String text, T classItem);
    T initializeFieldWithAttribute(String tagName, String attrName, String text,T classItem);
    T getClassItem();
    String getRootElementName();
}
