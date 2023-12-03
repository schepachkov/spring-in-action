package sia.tacocloud.tacos.jms;

import sia.tacocloud.tacos.data.entity.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(TacoOrder tacoOrder);
}
