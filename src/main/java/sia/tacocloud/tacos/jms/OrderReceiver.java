package sia.tacocloud.tacos.jms;

import javax.jms.JMSException;

public interface OrderReceiver {

    Object receive() throws JMSException;
}
