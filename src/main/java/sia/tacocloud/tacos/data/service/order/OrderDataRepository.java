package sia.tacocloud.tacos.data.service.order;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.data.entity.TacoOrder;

public interface OrderDataRepository extends OrderRepository, CrudRepository<TacoOrder, Long> {
}
