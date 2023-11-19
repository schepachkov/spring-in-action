package sia.tacocloud.tacos.data.service.taco;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.data.entity.Taco;

public interface TacoDataRepository extends TacoRepository, CrudRepository<Taco, Long> {}
