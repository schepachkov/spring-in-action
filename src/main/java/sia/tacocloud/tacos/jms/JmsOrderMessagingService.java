package sia.tacocloud.tacos.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import sia.tacocloud.tacos.data.entity.TacoOrder;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jmsTemplate;


    public JmsOrderMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public void sendOrder(TacoOrder order) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(order);
            }
        });
    }
}
