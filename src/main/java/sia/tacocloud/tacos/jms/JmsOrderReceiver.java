package sia.tacocloud.tacos.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sia.tacocloud.tacos.data.entity.TacoOrder;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class JmsOrderReceiver implements OrderReceiver {

  private JmsTemplate jmsTemplate;

  public JmsOrderReceiver(JmsTemplate jms) {
    this.jmsTemplate = jms;
  }

  @Override
  public TacoOrder receive() throws JMSException {
    Message msg = jmsTemplate.receive();
    TacoOrder body = msg.getBody(TacoOrder.class);
    return body;
  }
}
