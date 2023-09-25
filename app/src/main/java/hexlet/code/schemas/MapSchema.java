package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    Predicate<Object> currentSizePredicate;
    {
        init = o -> o == null || o instanceof Number;
        addRequirement(init);
    }
    @Override
    public void required() {
        Predicate<Object> newReq = o -> o instanceof Map;
        removeInitReq();
        addRequirement(newReq);
    }

    public void sizeof(int n) {
        removeSpecifiedReq(currentSizePredicate);
        currentSizePredicate = o -> o instanceof Map && ((Map<?, ?>) o).size() == n;
        addRequirement(currentSizePredicate);
    }
}
