package hexlet.code;

import hexlet.code.schemes.StringSchema;
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

        schema.required();

        assertThat(schema.isValid("resets")).isTrue();
    }
}
