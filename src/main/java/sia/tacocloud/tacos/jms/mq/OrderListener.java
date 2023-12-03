package sia.tacocloud.tacos.jms.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.data.entity.TacoOrder;

@Component
@Slf4j
public class OrderListener {

    @RabbitListener(queues = "tacocloud.order")
    public void receiveOrder(TacoOrder tacoOrder) {
      log.info("Retrieve taco order via listener (RabbitMQ) {}", tacoOrder);
    }
}
