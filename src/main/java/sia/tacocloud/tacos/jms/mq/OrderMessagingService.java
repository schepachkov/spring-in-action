package sia.tacocloud.tacos.jms.mq;

import sia.tacocloud.tacos.data.entity.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(TacoOrder tacoOrder);
}
