package sia.tacocloud.tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import sia.tacocloud.tacos.config.security.user_details.DbUser;
import sia.tacocloud.tacos.data.entity.Ingredient;
import sia.tacocloud.tacos.data.entity.Ingredient.Type;
import sia.tacocloud.tacos.data.entity.Taco;
import sia.tacocloud.tacos.data.entity.TacoOrder;
import sia.tacocloud.tacos.data.service.ingredient.IngredientRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

  private final IngredientRepository ingredientRepository;

  public DesignTacoController(@Qualifier("ingredientDataRepository") IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    Iterable<Ingredient> ingredients = ingredientRepository.findAll();
    List<Ingredient> ingredientList = new ArrayList<>();
    ingredients.forEach(ingredientList::add);
    for (Type type : Type.values()) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientList, type));
    }
  }

  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  @PostMapping
  public String processTaco(/*@Valid*/ Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
    if (errors.hasErrors()) {
      return "design";
    }
    tacoOrder.addTaco(taco);
    log.info("Processing taco: {}", taco);
    return "redirect:/orders/current";
  }

  private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
  }
}
