package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<SchemaName, Predicate<Object>> requirements = new LinkedHashMap<>();
    protected boolean required = false;

    public final boolean isValid(Object obj) {
        if (!required && obj == null) {
            return true;
        }
        for (Predicate<Object> req : requirements.values()) {
            if (!req.test(obj)) {
                return false;
            }
        }
        return true;
    }

    protected final void addRequirement(SchemaName name, Predicate<Object> req) {
        requirements.put(name, req);
    }
}
