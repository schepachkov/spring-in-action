package sia.tacocloud.tacos.web;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public TestController(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @PostMapping("/create")
    public Object createTest(@RequestBody String name) {
        String sql = "INSERT INTO test_table (name) VALUES (:name)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, parameters, keyHolder, new String[]{"id", "name"});
        return keyHolder.getKeyList();
    }

    @DeleteMapping("/delete-all")
    public Object deleteAll() {
        String sql = "DELETE FROM test_table";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(), keyHolder, new String[]{"id", "name"});
        return keyHolder.getKeyList();
    }
}
