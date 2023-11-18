package sia.tacocloud.tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.tacos.config.TacoOrderProps;
import sia.tacocloud.tacos.config.security.user_details.DbUser;
import sia.tacocloud.tacos.data.entity.TacoOrder;
import sia.tacocloud.tacos.data.service.order.OrderRepository;

@Controller
@RequestMapping("/orders")
@Slf4j
@SessionAttributes("tacoOrder")
public class OrderController {

  private final OrderRepository orderRepository;
  private final TacoOrderProps tacoOrderProps;

  public OrderController(@Qualifier("orderDataRepository") OrderRepository orderRepository, TacoOrderProps tacoOrderProps) {
    this.orderRepository = orderRepository;
    this.tacoOrderProps = tacoOrderProps;
  }

  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }

  @GetMapping("")
  public String orderForUser(@AuthenticationPrincipal DbUser user, Model model) {
    Pageable pageable = PageRequest.of(0, tacoOrderProps.getPageSize());
    model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
    return "orderList";
  }

  @PostMapping
  public String processOrder(/*@Valid*/ TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus, Authentication authentication) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    log.info("Order submitted: {}", tacoOrder);
    DbUser user = (DbUser) authentication.getPrincipal();
    tacoOrder.setUser(user);
    orderRepository.save(tacoOrder);
    sessionStatus.setComplete();
    return "redirect:/orders";
  }
}
