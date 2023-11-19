package sia.tacocloud.tacos.data.service.order;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sia.tacocloud.tacos.config.security.user_details.DbUser;
import sia.tacocloud.tacos.data.entity.TacoOrder;

import java.util.List;

public interface OrderDataRepository extends JpaRepository<TacoOrder, Long> {

    List<TacoOrder> findByUserOrderByPlacedAtDesc(DbUser user, Pageable pageable);
}
