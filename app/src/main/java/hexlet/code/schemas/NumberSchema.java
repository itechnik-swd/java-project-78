package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        checks.put("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        checks.put("positive", value -> value > 0);
        return this;
    }

    public void range(int min, int max) {
        checks.put("range", value -> value >= min && value <= max);
    }

    @Override
    public boolean isValid(Integer value) {
        return super.isValid(value);
    }
}
