package sia.tacocloud.tacos.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.tacos.config.security.user_details.DbUser;
import sia.tacocloud.tacos.constant.Msg;
import sia.tacocloud.tacos.constant.UserRoles;
import sia.tacocloud.tacos.data.service.user.UserRepository;

import java.util.Optional;

@Configuration
public class DbSecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository repository) {
    return username -> {
      Optional<DbUser> user = repository.findByUsername(username);
      return user.orElseThrow(() -> new UsernameNotFoundException(String.format(Msg.USER_NOT_FOUND_PATTERN.getPattern(), username)));
    };
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
    return security
        .authorizeRequests()
        .antMatchers("/design", "/orders")
        .hasRole(UserRoles.USER.getRoleWithoutPrefix())
        .antMatchers("/", "/**")
        .permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/design", false)
        .and()
        .logout()
        .and()
        .csrf()
        .disable()    // для того, чтобы пост запросы проходили без 403, иначе нужно отправлять на бек токен из формы
        .build();
  }
}
