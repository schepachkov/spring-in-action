package sia.tacocloud.tacos.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.data.entity.TacoOrder;
import sia.tacocloud.tacos.data.service.order.OrderDataRepository;


@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderApiController {

  private final OrderDataRepository orderRepository;


  public OrderApiController(OrderDataRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public TacoOrder postOrder(@RequestBody TacoOrder tacoOrder) {
    return orderRepository.save(tacoOrder);
  }

  @GetMapping
  @RequestMapping("get-msg")
  public TacoOrder getTacoOrder() {
    return null;
  }
}
