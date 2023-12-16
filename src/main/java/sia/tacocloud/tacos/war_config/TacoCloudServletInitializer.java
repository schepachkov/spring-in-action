package sia.tacocloud.tacos.war_config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import sia.tacocloud.TacoCloudApplication;

public class TacoCloudServletInitializer extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(TacoCloudApplication.class);
  }
}
