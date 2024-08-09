package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected abstract BaseSchema<T> required();

    protected boolean isValid(T value) {
        if (value == null) {
            return !checks.containsKey("required");
        }
        return checks.entrySet().stream()
                .allMatch(entry -> entry.getValue().test(value));
    }
}
