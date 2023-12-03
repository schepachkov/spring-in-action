package sia.tacocloud.tacos.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    @KafkaListener(topics = "taco-cloud", groupId = "0")
    public void consume(Message<String> msg) {
      log.info("Got message - {}", msg);
    }

}
