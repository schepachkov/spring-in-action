package sia.tacocloud.tacos.web;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.tacos.data.entity.Taco;
import sia.tacocloud.tacos.data.entity.TacoOrder;
import sia.tacocloud.tacos.data.service.order.OrderDataRepository;
import sia.tacocloud.tacos.data.service.order.OrderRepository;
import sia.tacocloud.tacos.data.service.taco.TacoDataRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
public class TacoController {

  private final TacoDataRepository tacoRepository;
  private final OrderDataRepository orderDataRepository;

  public TacoController(TacoDataRepository tacoRepository, OrderDataRepository orderDataRepository) {
    this.tacoRepository = tacoRepository;
    this.orderDataRepository = orderDataRepository;
  }

  @GetMapping(params = "recent")
  public Iterable<Taco> recentTacos() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    return tacoRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Taco> findById(@PathVariable("id") Long id) {
    Optional<Taco> tacoOpt = tacoRepository.findById(id);
    if (tacoOpt.isPresent()) {
      return ResponseEntity.ok(tacoOpt.get());
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepository.save(taco);
  }

  @PutMapping(path = "/{orderId}", consumes = "application/json")
  public TacoOrder putOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder order) {
    order.setId(orderId);
    return orderDataRepository.save(order);
  }

  @PatchMapping(path = "/{orderId}", consumes = "application/json")
  public TacoOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch) {
    TacoOrder order = orderDataRepository.findById(orderId).get();
    if (patch.getDeliveryName() != null) {
      order.setDeliveryName(patch.getDeliveryName());
    }
    if (patch.getDeliveryStreet() != null) {
      order.setDeliveryStreet(patch.getDeliveryStreet());
    }
    if (patch.getDeliveryCity() != null) {
      order.setDeliveryCity(patch.getDeliveryCity());
    }
    if (patch.getDeliveryState() != null) {
      order.setDeliveryState(patch.getDeliveryState());
    }
    if (patch.getDeliveryZip() != null) {
      order.setDeliveryZip(patch.getDeliveryZip());
    }
    if (patch.getCcNumber() != null) {
      order.setCcNumber(patch.getCcNumber());
    }
    if (patch.getCcExpiration() != null) {
      order.setCcExpiration(patch.getCcExpiration());
    }
    if (patch.getCcCVV() != null) {
      order.setCcCVV(patch.getCcCVV());
    }
    return orderDataRepository.save(order);
  }
}
