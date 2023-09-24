package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    {
        init = o -> o == null || o instanceof Number;
        addRequirement(init);
    }
    @Override
    public void required() {
        Predicate<Object> newReq = o -> o instanceof Integer;
        removeInitReq();
        addRequirement(newReq);
    }

    public void positive() {
        Predicate<Object> newReq = o -> o == null || (Integer) o > 0;
        addRequirement(newReq);
    }

    public void range(int n1, int n2) {
        Predicate<Object> newReq = o -> isInRange(n1, n2, (Integer) o);
        addRequirement(newReq);
    }

    private boolean isInRange(int floor, int ceil, int target) {
        return target >= floor && target <= ceil;
    }
}
