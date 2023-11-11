package sia.tacocloud.tacos.data.service.ingredient;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.tacos.data.entity.Ingredient;

public interface IngredientDataRepository extends IngredientRepository, CrudRepository<Ingredient, String> {
}
