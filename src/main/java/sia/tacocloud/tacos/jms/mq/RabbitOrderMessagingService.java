package sia.tacocloud.tacos.jms.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloud.tacos.data.entity.TacoOrder;

@Service
@Slf4j
public class RabbitOrderMessagingService implements OrderMessagingService {

    private final RabbitTemplate rabbit;

    public RabbitOrderMessagingService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void sendOrder(TacoOrder tacoOrder) {
        log.info("Send to JMS {}.", tacoOrder);
        rabbit.convertAndSend(JmsConst.ROUTING_KEY.getValue(), tacoOrder, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("X_ORDER_SOURCE", "WEB");
                return message;
            }
        });
    }
}
