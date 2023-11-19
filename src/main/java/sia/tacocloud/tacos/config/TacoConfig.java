package sia.tacocloud.tacos.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.tacocloud.tacos.data.entity.Ingredient;
import sia.tacocloud.tacos.data.entity.Ingredient.Type;
import sia.tacocloud.tacos.data.entity.Taco;
import sia.tacocloud.tacos.data.service.ingredient.IngredientRepository;
import sia.tacocloud.tacos.data.service.taco.TacoDataRepository;
import sia.tacocloud.tacos.data.service.taco.TacoRepository;
import sia.tacocloud.tacos.data.service.user.UserRepository;

import java.util.Arrays;

@Configuration
public class TacoConfig {

  private final IngredientRepository ingredientRepository;

  public TacoConfig(@Qualifier("ingredientDataRepository") IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  //@Bean
  @Profile("DEV")
  public ApplicationRunner commandLineRunner() {
    return args -> {
      ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
      ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
      ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
      ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
      ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
      ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
      ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
      ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
      ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
      ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    };
  }

  @Bean
  @Profile("DEV")
  public CommandLineRunner dataLoader(TacoDataRepository tacoRepo) {
    return args -> {
      Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
      Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
      Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
      Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
      Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
      Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
      Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
      Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
      Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
      Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
      ingredientRepository.save(flourTortilla);
      ingredientRepository.save(cornTortilla);
      ingredientRepository.save(groundBeef);
      ingredientRepository.save(carnitas);
      ingredientRepository.save(tomatoes);
      ingredientRepository.save(lettuce);
      ingredientRepository.save(cheddar);
      ingredientRepository.save(jack);
      ingredientRepository.save(salsa);
      ingredientRepository.save(sourCream);
      Taco taco1 = new Taco();
      taco1.setName("Carnivore");
      taco1.setIngredients(
          Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
      tacoRepo.save(taco1);
      Taco taco2 = new Taco();
      taco2.setName("Bovine Bounty");
      taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
      tacoRepo.save(taco2);
      Taco taco3 = new Taco();
      taco3.setName("Veg-Out");
      taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
      tacoRepo.save(taco3);
    };
  }
}
