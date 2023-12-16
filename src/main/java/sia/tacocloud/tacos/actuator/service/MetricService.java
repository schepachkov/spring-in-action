package sia.tacocloud.tacos.actuator.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import sia.tacocloud.tacos.data.entity.Ingredient;
import sia.tacocloud.tacos.data.entity.Taco;

import java.util.List;

@Service
public class MetricService {

    private final MeterRegistry meterRegistry;

    public MetricService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void refreshIngredientCounter(Taco taco) {
        List<Ingredient> ingredients = taco.getIngredients();
        for (Ingredient ingredient : ingredients) {
            meterRegistry.counter("tacocloud", "ingredient", ingredient.getId()).increment();
        }
    }
}
