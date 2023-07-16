package sia.tacocloud.tacos.data.order;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.TacoOrder;

public interface OrderDataRepository extends OrderRepository, CrudRepository<TacoOrder, Long> {
}
