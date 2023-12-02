package sia.tacocloud.tacos.jms.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sia.tacocloud.tacos.data.entity.TacoOrder;

@Component
@Slf4j
public class RabbitOrderReceiver {

  private final RabbitTemplate rabbitTemplate;

  public RabbitOrderReceiver(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public TacoOrder receiveTacoOrder() {
    log.info("Receiving taco order..");
    TacoOrder tacoOrder =
        rabbitTemplate.receiveAndConvert(
            JmsConst.ROUTING_KEY.getValue(), new ParameterizedTypeReference<TacoOrder>() {});
    log.info("has got taco order as object {}", tacoOrder);
    return tacoOrder;
  }
}
