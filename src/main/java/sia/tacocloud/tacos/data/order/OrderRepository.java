package sia.tacocloud.tacos.data.order;

import sia.tacocloud.tacos.TacoOrder;

public interface OrderRepository {
  TacoOrder save(TacoOrder order);
}
