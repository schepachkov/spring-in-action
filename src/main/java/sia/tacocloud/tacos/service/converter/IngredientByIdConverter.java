package sia.tacocloud.tacos.service.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.data.entity.Ingredient;
import sia.tacocloud.tacos.data.service.ingredient.IngredientRepository;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private final IngredientRepository ingredientRepository;

  @Autowired
  public IngredientByIdConverter(@Qualifier("ingredientDataRepository") IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @Override
  public Ingredient convert(String id) {
    return ingredientRepository.findById(id).orElse(null);
  }
}
