package hexlet.code.schemes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected Predicate<Object> init;
    private final List<Predicate<Object>> requirements = new ArrayList<>();

    abstract void required();

    public boolean isValid(Object obj) {
        for (Predicate<Object> req : requirements) {
            if (!req.test(obj)) {
                return false;
            }
        }
        return true;
    }

    protected void addRequirement(Predicate<Object> req) {
        requirements.add(req);
    }

    protected void removeInitReq() {
        requirements.remove(init);
    }
}
