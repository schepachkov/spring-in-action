package sia.tacocloud.tacos.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;
import sia.tacocloud.tacos.data.entity.Ingredient;
import sia.tacocloud.tacos.data.entity.Taco;

import java.util.List;

@Component
// это будет работать видимо только с использованием data-rest эндпоинтов или с доп настройками
public class TacoMetrics extends AbstractRepositoryEventListener<Taco> {
    
    private final MeterRegistry meterRegistry;

    public TacoMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    protected void onAfterCreate(Taco taco) {
        List<Ingredient> ingredients = taco.getIngredients();
        for (Ingredient ingredient : ingredients) {
            meterRegistry.counter("tacocloud_data_rest", "ingredient_data_rest", ingredient.getId()).increment();
        }
    }
}
