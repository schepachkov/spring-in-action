package sia.tacocloud.tacos.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "test_table")
@Entity
@Data
public class TestTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer id;
    private final String name;
    private final LocalDateTime time;
}
