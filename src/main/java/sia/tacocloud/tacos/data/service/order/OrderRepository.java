package sia.tacocloud.tacos.data.service.order;

import org.springframework.data.domain.Pageable;
import sia.tacocloud.tacos.config.security.user_details.DbUser;
import sia.tacocloud.tacos.data.entity.TacoOrder;

import java.util.List;

public interface OrderRepository {
  TacoOrder save(TacoOrder order);

  List<TacoOrder> findByUserOrderByPlacedAtDesc(DbUser user, Pageable pageable);
}
