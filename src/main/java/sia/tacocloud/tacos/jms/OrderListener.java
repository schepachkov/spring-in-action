package sia.tacocloud.tacos.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.data.entity.TacoOrder;

@Component
@Slf4j
public class OrderListener {

    @JmsListener(destination = "tacocloud.order.queue")
    public void jmsListener(TacoOrder tacoOrder) {
        log.info("Recieved from JMS: {}", tacoOrder);
    }
}
