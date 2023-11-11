package sia.tacocloud.tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.tacos.config.security.user_details.DbUser;
import sia.tacocloud.tacos.data.entity.TacoOrder;
import sia.tacocloud.tacos.data.service.order.OrderRepository;

import javax.validation.Valid;
import java.util.Random;

@Controller
@RequestMapping("/orders")
@Slf4j
@SessionAttributes("tacoOrder")
public class OrderController {

  private final OrderRepository orderRepository;

  public OrderController(@Qualifier("orderDataRepository") OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus, Authentication authentication) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    log.info("Order submitted: {}", tacoOrder);
    DbUser user = (DbUser) authentication.getPrincipal();
    tacoOrder.setUser(user);
    orderRepository.save(tacoOrder);
    sessionStatus.setComplete();
    return "redirect:/";
  }
}
