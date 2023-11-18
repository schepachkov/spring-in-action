package sia.tacocloud.tacos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Slf4j
@Validated
public class TacoOrderProps {

    @Min(value=5, message="must be between 5 and 25")
    @Max(value=25, message="must be between 5 and 25")
    private int pageSize = 20;

    public int getPageSize() {
        log.info("Получение значения 'pageSize'. pageSize={}", pageSize);
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        log.info("Смена значения pageSize. Было - {}, стало - {}", this.pageSize, pageSize);
        this.pageSize = pageSize;
    }
}
