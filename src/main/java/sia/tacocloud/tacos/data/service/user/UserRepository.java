package sia.tacocloud.tacos.data.service.user;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.config.security.user_details.DbUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<DbUser, Long> {

    Optional<DbUser> findByUsername(String username);
}
