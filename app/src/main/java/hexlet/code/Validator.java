package hexlet.code;

import hexlet.code.schemes.StringSchema;
import hexlet.code.schemes.NumberSchema;

public final class Validator {
    public StringSchema string() {
        return new StringSchema();
    }
    public NumberSchema number() {
        return new NumberSchema();
    }
}
