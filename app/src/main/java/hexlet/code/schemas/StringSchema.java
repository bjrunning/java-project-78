package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        Predicate<Object> init = o -> o instanceof String;
        addRequirement(SchemaName.INITIAL, init);
    }

    public StringSchema required() {
        required = true;
        Predicate<Object> requiredString = o -> !o.toString().isEmpty();
        addRequirement(SchemaName.REQUIRED, requiredString);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<Object> newReq = o -> o.toString().contains(str);
        addRequirement(SchemaName.CONTAINS, newReq);
        return this;
    }

    public StringSchema minLength(int num) {
        Predicate<Object> newReq = o -> o.toString().length() >= num;
        addRequirement(SchemaName.MIN_LENGTH, newReq);
        return this;
    }
}
