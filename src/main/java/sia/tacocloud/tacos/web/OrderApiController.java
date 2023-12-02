package sia.tacocloud.tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.data.entity.TacoOrder;
import sia.tacocloud.tacos.data.service.order.OrderDataRepository;
import sia.tacocloud.tacos.jms.mq.OrderMessagingService;
import sia.tacocloud.tacos.jms.mq.RabbitOrderReceiver;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderApiController {

  private final OrderDataRepository orderRepository;
  private final OrderMessagingService orderMessagingService;

  @Autowired
  private RabbitOrderReceiver rabbitOrderReceiver;

  public OrderApiController(OrderDataRepository orderRepository, OrderMessagingService orderMessagingService) {
    this.orderRepository = orderRepository;
    this.orderMessagingService = orderMessagingService;
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public TacoOrder postOrder(@RequestBody TacoOrder tacoOrder) {
    orderMessagingService.sendOrder(tacoOrder);
    //return orderRepository.save(tacoOrder);
    return tacoOrder;
  }

  @GetMapping
  @RequestMapping("get-msg")
  public TacoOrder getTacoOrder() {
    return rabbitOrderReceiver.receiveTacoOrder();
  }
}
