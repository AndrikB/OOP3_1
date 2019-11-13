package lab2;


public class Tariff implements Comparable<Tariff>{

    private String id;
    private String name;
    private Operator operatorName;
    private float payroll;
    private Price insideNetworkPrice;
    private Price outsideNetworkPrice;
    private Price landlinePrice;
    private Price smsPrice;
    private boolean selectedNumberPresence;
    private String tariffication;
    private float tariffConectionPrice;

    @Override
    public int compareTo(Tariff o) {
        if(this ==  null || o == null) return -1;
        if(operatorName == null) return -1;
        if(o.operatorName == null) return -1;
        if(operatorName.compareTo(o.operatorName) == 0) {
            if(name == null) return -1;
            return name.compareTo(o.name);
        }
        return operatorName.compareTo(o.operatorName);
    }

    protected class Price {
        private String currency;
        private float price;
        private int tariffAmount;

        Price(){
            currency = null;
            price = 0;
            tariffAmount = 0;
        }

        @Override
        public String toString() {
            return "Price{" + "currency=" + currency + ", price="
                    + price + ", tariffAmount=" + tariffAmount + '}';
        }


        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getTariffAmount() {
            return tariffAmount;
        }

        public void setTariffAmount(int tariffAmount) {
            this.tariffAmount = tariffAmount;
        }

        public Price(String currency, float price, int tariffAmount) {
            this.currency = currency;
            this.price = price;
            this.tariffAmount = tariffAmount;
        }
    }

    public Tariff(String id, String name, Operator operatorName, float payroll,
                  Price insideNetworkPrice, Price outsideNetworkPrice,
                  Price landlinePrice, Price smsPrice, boolean selectedNumberPresence,
                  String tariffication, float tariffConectionPrice) {

        this.id = id;
        this.name = name;
        this.operatorName = operatorName;
        this.payroll = payroll;
        this.insideNetworkPrice = insideNetworkPrice;
        this.outsideNetworkPrice = outsideNetworkPrice;
        this.landlinePrice = landlinePrice;
        this.smsPrice = smsPrice;
        this.selectedNumberPresence = selectedNumberPresence;
        this.tariffication = tariffication;
        this.tariffConectionPrice = tariffConectionPrice;
    }

    public Tariff() {
        insideNetworkPrice = new Price();
        outsideNetworkPrice = new Price();
        landlinePrice = new Price();
        smsPrice = new Price();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Operator getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(Operator operatorName) {
        this.operatorName = operatorName;
    }

    public float getPayroll() {
        return payroll;
    }

    public void setPayroll(float payroll) {
        this.payroll = payroll;
    }

    public Price getInsideNetworkPrice() {
        return insideNetworkPrice;
    }

    public void setInsideNetworkPrice(Price insideNetworkPrice) {
        this.insideNetworkPrice = insideNetworkPrice;
    }

    public Price getOutsideNetworkPrice() {
        return outsideNetworkPrice;
    }

    public void setOutsideNetworkPrice(Price outsideNetworkPrice) {
        this.outsideNetworkPrice = outsideNetworkPrice;
    }

    public Price getLandlinePrice() {
        return landlinePrice;
    }

    public void setLandlinePrice(Price landlinePrice) {
        this.landlinePrice = landlinePrice;
    }

    public Price getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(Price smsPrice) {
        this.smsPrice = smsPrice;
    }

    public boolean isSelectedNumberPresence() {
        return selectedNumberPresence;
    }

    public void setSelectedNumberPresence(boolean selectedNumberPresence) {
        this.selectedNumberPresence = selectedNumberPresence;
    }

    public String getTariffication() {
        return tariffication;
    }

    public void setTariffication(String tariffication) {
        this.tariffication = tariffication;
    }

    public float getTariffConectionPrice() {
        return tariffConectionPrice;
    }

    public void setTariffConectionPrice(float tariffConectionPrice) {
        this.tariffConectionPrice = tariffConectionPrice;
    }

    @Override
    public String toString() {
        return "Tariff{" + "id=" + id +",\n"
                + " name=" + name + ",\n"
                + " operatorName=" + operatorName + ",\n"
                + " payroll=" + payroll + ",\n"
                + " insideNetworkPrice=" + insideNetworkPrice + ",\n"
                + " outsideNetworkPrice=" + outsideNetworkPrice + ",\n"
                + " landlinePrice=" + landlinePrice + ",\n"
                + " smsPrice=" + smsPrice + ",\n"
                + " selectedNumberPresence=" + selectedNumberPresence + ",\n"
                + " tariffication=" + tariffication + ",\n"
                + " tariffConectionPrice=" + tariffConectionPrice + "}\n\n\n";
    }


}
