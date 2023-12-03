package sia.tacocloud.tacos.jms;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.data.entity.TacoOrder;

@Component
@Slf4j
public class KafkaOrderMessagingService implements OrderMessagingService {

    private final KafkaTemplate<String, String> kafkaTemplate;


    public KafkaOrderMessagingService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrder tacoOrder) {
        log.info("Send taco order via Kafka {}", tacoOrder);
        kafkaTemplate.send("taco-cloud", "test message kek");
    }
}
