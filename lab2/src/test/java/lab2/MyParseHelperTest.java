package lab2;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyParseHelperTest {

    private Parseable<Tariff> helper = new MyParseHelper();


    @Test
    public void testInitializeFieldWithTag() {
        System.out.println("initializeFieldWithTag");
        Tariff tariff = new Tariff();
        tariff = helper.initializeFieldWithTag(TagNames.NAME, "myName", tariff);
        assertEquals(tariff.getName(), "myName");
        tariff = helper.initializeFieldWithTag(TagNames.SELECTED_NUMBER_PRESENCE, "no", tariff);
        assertEquals(tariff.getName(), "myName");
        assertEquals(tariff.isSelectedNumberPresence(), false);
        assertEquals(tariff.getOperatorName(), null);
    }

    @Test
    public void testInitializeFieldWithAttribute() {
        System.out.println("initializeFieldWithAttribute");
        System.out.println("initializeFieldWithTag");
        Tariff tariff = new Tariff();
        tariff = helper.initializeFieldWithAttribute(TagNames.INSIDE_NETWORK_PRICE, AttributeNames.CURRENCY, "currency", tariff);
        assertEquals(tariff.getInsideNetworkPrice().getCurrency(), "currency");
        tariff = helper.initializeFieldWithAttribute(TagNames.TARIFF, AttributeNames.ID, "ID", tariff);
        assertEquals(tariff.getId(), "ID");
        assertEquals(tariff.getOutsideNetworkPrice().getCurrency(), null);
    }

    @Test
    public void testGetClassItem() {
        System.out.println("getClassItem");
        assertEquals(helper.getClassItem().getClass().getName(), "lab2.Tariff");
    }

    @Test
    public void testGetRootElementName() {
        System.out.println("getRootElementName");
        assertEquals(helper.getRootElementName(), TagNames.TARIFF);
    }

}
