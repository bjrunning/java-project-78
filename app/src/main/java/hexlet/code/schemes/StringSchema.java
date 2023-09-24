package hexlet.code.schemes;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    {
        init = o -> o == null || o instanceof String && ((String) o).isEmpty();
        addRequirement(init);
    }
    @Override
    public void required() {
        Predicate<Object> newReq = o -> o instanceof String && !((String) o).isEmpty();
        removeInitReq();
        addRequirement(newReq);
    }

    public void contains(String str) {
        Predicate<Object> newReq = o -> ((String) o).contains(str);
        addRequirement(newReq);
    }

    public void minLength(int num) {
        Predicate<Object> newReq = o -> o.toString().length() >= num;
        addRequirement(newReq);
    }
}
