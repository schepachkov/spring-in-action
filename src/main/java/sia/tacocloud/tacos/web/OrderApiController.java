package sia.tacocloud.tacos.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.data.entity.TacoOrder;
import sia.tacocloud.tacos.data.service.order.OrderDataRepository;
import sia.tacocloud.tacos.jms.JmsOrderReceiver;
import sia.tacocloud.tacos.jms.OrderMessagingService;

import javax.jms.JMSException;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderApiController {

  private final OrderDataRepository orderRepository;
  private final OrderMessagingService messageService;
  private final JmsOrderReceiver jmsOrderReceiver;

  public OrderApiController(
      OrderDataRepository orderRepository,
      OrderMessagingService messageService,
      JmsOrderReceiver jmsOrderReceiver) {
    this.orderRepository = orderRepository;
    this.messageService = messageService;
    this.jmsOrderReceiver = jmsOrderReceiver;
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public TacoOrder postOrder(@RequestBody TacoOrder order) {
    messageService.sendOrder(order);
    return orderRepository.save(order);
  }

  @GetMapping
  @RequestMapping("get-msg")
  public TacoOrder getTacoOrder() throws JMSException {
    return jmsOrderReceiver.receive();
  }
}
