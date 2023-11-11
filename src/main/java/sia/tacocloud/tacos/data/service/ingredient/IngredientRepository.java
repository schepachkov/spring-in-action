package sia.tacocloud.tacos.data.service.ingredient;

import sia.tacocloud.tacos.data.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

  Iterable<Ingredient> findAll();
  Optional<Ingredient> findById(String id);
  Ingredient save(Ingredient ingredient);
}
