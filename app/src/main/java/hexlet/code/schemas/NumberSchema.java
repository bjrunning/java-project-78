package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Predicate<Object> init = o -> o instanceof Number;
        addRequirement(SchemaName.INITIAL, init);
    }

    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> newReq = o -> (Integer) o > 0;
        addRequirement(SchemaName.POSITIVE, newReq);
        return this;
    }

    public NumberSchema range(int n1, int n2) {
        Predicate<Object> newReq = o -> isInRange(n1, n2, (Integer) o);
        addRequirement(SchemaName.RANGE, newReq);
        return this;
    }

    private boolean isInRange(int floor, int ceil, int target) {
        return target >= floor && target <= ceil;
    }
}
