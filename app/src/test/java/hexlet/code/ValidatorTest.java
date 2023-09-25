package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    @Test
    void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid(5)).isFalse();
        assertThat(schema.isValid(new HashMap<>(Map.of(2, 2)))).isFalse();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("1212")).isTrue();

        schema.minLength(5);

        assertThat(schema.isValid("five")).isFalse();
        assertThat(schema.isValid("fives")).isTrue();

        schema.contains("TARGET");

        assertThat(schema.isValid("somewhere here is the TARGET")).isTrue();
        assertThat(schema.isValid("somewhere here is the TARG-")).isFalse();

        schema.contains("bloated");

        assertThat(schema.isValid("this TARGET scheme is now reassigned")).isFalse();
        assertThat(schema.isValid("this schema cannot be bloated")).isTrue();

        assertThat(schema.isValid(12435)).isFalse();
    }

    @Test
    void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("5")).isFalse();

        schema.positive();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(-1)).isFalse();
        assertThat(schema.isValid("5")).isFalse();
        assertThat(schema.isValid(1)).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();

        schema.range(5, 10);

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();

        schema.range(7, 12);

        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(11)).isTrue();

        assertThat(schema.isValid("TEN")).isFalse();
    }

    @Test
    void testMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
        assertThat(schema.isValid(new HashMap<>(Map.of(1, 1)))).isTrue();

        schema.sizeof(2);

        assertThat(schema.isValid(new HashMap<>(Map.of(1, 1)))).isFalse();
        assertThat(schema.isValid(new HashMap<>(Map.of(1, 1, 2, 2)))).isTrue();

        schema.sizeof(1);

        assertThat(schema.isValid(new HashMap<>(Map.of(1, 1)))).isTrue();
        assertThat(schema.isValid(new HashMap<>(Map.of(1, 1, 2, 2)))).isFalse();
    }

    @Test
    void testMapShape() {

        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(schema.isValid(human1)).isTrue();


        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human2.put("name", "");
        human2.put("age", null);
        assertThat(schema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human2.put("name", "Valya");
        human2.put("age", -5);
        assertThat(schema.isValid(human4)).isFalse();

        assertThat(schema.isValid(1)).isFalse();
    }
}
