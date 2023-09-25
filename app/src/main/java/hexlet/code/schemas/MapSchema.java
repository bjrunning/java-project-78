package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> init = o -> o instanceof Map<?, ?>;
        addRequirement(SchemaName.INITIAL, init);
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> currentSizePredicate = o -> ((Map<?, ?>) o).size() == size;
        addRequirement(SchemaName.SIZE_OF, currentSizePredicate);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemeMap) {
        Predicate<Object> currentShapePredicate = o -> schemeMap
                .entrySet()
                .stream()
                .allMatch(entry -> {
                    Object valueToCheck = ((Map<?, ?>) o).get(entry.getKey());
                    return entry.getValue().isValid(valueToCheck);
                });

        addRequirement(SchemaName.SHAPE, currentShapePredicate);
        return this;
    }
}
