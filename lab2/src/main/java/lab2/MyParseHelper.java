/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.function.Supplier;

public class MyParseHelper implements Parseable<Tariff> {

    private final Supplier<Tariff> constructor;

    public MyParseHelper() {
        this.constructor = Tariff::new;
    }

    @Override
    public Tariff initializeFieldWithTag(String tagName, String text, Tariff classItem) {
        Tariff tariff = classItem;
        switch (tagName) {
            case TagNames.NAME:
                tariff.setName(text);
                break;
            case TagNames.OPERATOR_NAME:
                tariff.setOperatorName(Enum.valueOf(Operator.class,
                        text.toUpperCase()));
                break;
            case TagNames.PAYROLL:
                tariff.setPayroll(Float.parseFloat(text));
                break;
            case TagNames.INSIDE_NETWORK_PRICE:
                tariff.getInsideNetworkPrice().setPrice(Float.parseFloat(text));
                break;
            case TagNames.OUTSIDE_NETWORK_PRICE:
                tariff.getOutsideNetworkPrice().setPrice(Float.parseFloat(text));
                break;
            case TagNames.LANDLINE_PRICE:
                tariff.getLandlinePrice().setPrice(Float.parseFloat(text));
                break;
            case TagNames.SMS_PRICE:
                tariff.getSmsPrice().setPrice(Float.parseFloat(text));
                break;
            case TagNames.SELECTED_NUMBER_PRESENCE:
                if (text.equals("yes")) {
                    tariff.setSelectedNumberPresence(true);
                } else {
                    tariff.setSelectedNumberPresence(false);
                }
                break;
            case TagNames.TARIFFICATION:
                tariff.setTariffication(text);
                break;
            case TagNames.TARIFF_CONECTION_PRICE:
                tariff.setTariffConectionPrice(Float.parseFloat(text));
                break;
            default:
                break;
        }
        return tariff;
    }

    @Override
    public Tariff initializeFieldWithAttribute(String tagName, String attrName, String text, Tariff classItem) {
        Tariff tariff = classItem;
        switch (tagName) {
            case TagNames.TARIFF:
                tariff.setId(text);
                break;
            case TagNames.INSIDE_NETWORK_PRICE:
                if (attrName.equals(AttributeNames.CURRENCY)) {
                    tariff.getInsideNetworkPrice().setCurrency(text);
                } else if (attrName.equals(AttributeNames.TARIFF_AMOUNT)) {
                    tariff.getInsideNetworkPrice().setTariffAmount(Integer.parseInt(text));
                }
                break;
            case TagNames.OUTSIDE_NETWORK_PRICE:
                if (attrName.equals(AttributeNames.CURRENCY)) {
                    tariff.getOutsideNetworkPrice().setCurrency(text);
                } else if (attrName.equals(AttributeNames.TARIFF_AMOUNT)) {
                    tariff.getOutsideNetworkPrice().setTariffAmount(Integer.parseInt(text));
                }
                break;
            case TagNames.LANDLINE_PRICE:
                if (attrName.equals(AttributeNames.CURRENCY)) {
                    tariff.getLandlinePrice().setCurrency(text);
                } else if (attrName.equals(AttributeNames.TARIFF_AMOUNT)) {
                    tariff.getLandlinePrice().setTariffAmount(Integer.parseInt(text));
                }
                break;
            case TagNames.SMS_PRICE:
                if (attrName.equals(AttributeNames.CURRENCY)) {
                    tariff.getSmsPrice().setCurrency(text);
                } else if (attrName.equals(AttributeNames.TARIFF_AMOUNT)) {
                    tariff.getSmsPrice().setTariffAmount(Integer.parseInt(text));
                }
                break;
            default:
                break;
        }
        return tariff;
    }

    @Override
    public Tariff getClassItem() {
        return constructor.get();
    }

    @Override
    public String getRootElementName() {
        return TagNames.TARIFF;
    }

}

class TagNames {

    public static final String TARIFF = "tariff";
    public static final String NAME = "name";
    public static final String OPERATOR_NAME = "operatorName";
    public static final String PAYROLL = "payroll";
    public static final String CALLPRICES = "callPrices";
    public static final String SMS_PRICE = "smsPrice";
    public static final String INSIDE_NETWORK_PRICE = "insideNetworkPrice";
    public static final String OUTSIDE_NETWORK_PRICE = "outsideNetworkPrice";
    public static final String LANDLINE_PRICE = "landlinePrice";
    public static final String PARAMETERS = "parameters";
    public static final String SELECTED_NUMBER_PRESENCE = "selectedNumberPresence";
    public static final String TARIFFICATION = "tariffication";
    public static final String TARIFF_CONECTION_PRICE = "tariffConectionPrice";
}

class AttributeNames {

    public static final String TARIFF_AMOUNT = "tariffAmount";
    public static final String CURRENCY = "currency";
    public static final String ID = "id";
}
