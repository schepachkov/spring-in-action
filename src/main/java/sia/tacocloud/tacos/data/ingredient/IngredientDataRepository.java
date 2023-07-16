package sia.tacocloud.tacos.data.ingredient;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.Ingredient;

public interface IngredientDataRepository extends IngredientRepository, CrudRepository<Ingredient, String> {
}
