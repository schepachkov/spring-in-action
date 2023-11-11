package sia.tacocloud.tacos.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.tacocloud.tacos.config.security.user_details.DbUser;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class RegistrationForm {

  private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

  private String username;
  private String password;
  private String fullname;
  private String street;
  private String city;
  private String state;
  private String zip;
  private String phone;

  public DbUser toUser(PasswordEncoder passwordEncoder) {
    return new DbUser(ID_GENERATOR.addAndGet(1), username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
  }
}
