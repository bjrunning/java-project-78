package hexlet.code.schemes;

import java.util.function.Predicate;

public class StringSchema extends Schema {
    {
        Predicate<Object> stringValidation = o -> o == null || o instanceof String && ((String) o).isEmpty();
        addRequirement(stringValidation);
    }
    @Override
    public void required() {
        Predicate<Object> newReq = o -> o instanceof String && !((String) o).isEmpty();
        removeAllPredicates();
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
