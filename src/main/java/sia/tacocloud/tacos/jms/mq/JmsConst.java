package sia.tacocloud.tacos.jms.mq;

public enum JmsConst {

    ROUTING_KEY("tacocloud.order");

    private String value;

    JmsConst(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
