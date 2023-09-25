package hexlet.code;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
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

        assertThat(schema.isValid("this TARGET is bloated now")).isTrue();
        assertThat(schema.isValid("this schema is bloated now")).isFalse();

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
        assertThat(schema.isValid(11)).isFalse();
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
}
