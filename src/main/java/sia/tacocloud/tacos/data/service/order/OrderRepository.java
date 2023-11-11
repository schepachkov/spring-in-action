package sia.tacocloud.tacos.data.service.order;

import sia.tacocloud.tacos.data.entity.TacoOrder;

public interface OrderRepository {
  TacoOrder save(TacoOrder order);
}
