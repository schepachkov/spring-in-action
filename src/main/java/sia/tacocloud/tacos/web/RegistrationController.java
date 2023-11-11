package sia.tacocloud.tacos.web;


import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.tacos.data.service.user.UserRepository;
import sia.tacocloud.tacos.dto.RegistrationForm;

import javax.sql.DataSource;

@Controller
@RequestMapping("/register")
public class RegistrationController {

  public static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

  private UserRepository userRepo;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder, HikariDataSource dataSource) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping
  public String registerForm() {
    return "registration";
  }

  @PostMapping
  public String processRegistration(RegistrationForm form) {
    userRepo.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }
}