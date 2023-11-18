package sia.tacocloud.tacos.data.service.order;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.config.security.user_details.DbUser;
import sia.tacocloud.tacos.data.entity.TacoOrder;

import java.util.List;

public interface OrderDataRepository extends OrderRepository, CrudRepository<TacoOrder, Long> {

}
