package sia.tacocloud.tacos.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.tacos.config.security.user_details.InMemoryUser;
import sia.tacocloud.tacos.constant.UserRoles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Configuration
public class InMemorySecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    List<UserDetails> users = new ArrayList<>();
    users.add(
        new InMemoryUser(
            "dima",
            passwordEncoder.encode("pass"),
            Arrays.asList(new SimpleGrantedAuthority(UserRoles.USER.getRoleWithPrefix()))));
    users.add(
        new InMemoryUser(
            "misha",
            passwordEncoder.encode("pass"),
            Arrays.asList(new SimpleGrantedAuthority(UserRoles.USER.getRoleWithPrefix()))));
    return new InMemoryUserDetailsManager(users);
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
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/design", false)
        .and()
        .logout()
        .and()
        .build();
  }
}
