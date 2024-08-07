package hexlet.code.schemas;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema {
    Map<String, Predicate<String>> checks = new LinkedHashMap<>();

    public void required() {
        Predicate<String> predicate = value -> value != null && !value.isEmpty();
        checks.put("required", predicate);
    }

    public StringSchema minLength(int minLength) {
        Predicate<String> predicate = value -> value.length() >= minLength;
        checks.put("minLength", predicate);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> predicate = value -> value.contains(substring);
        checks.put("contains", predicate);
        return this;
    }

    public boolean isValid(String value) {
        Collection<Predicate<String>> predicates = checks.values();

        for (var predicate : predicates) {
            if (!predicate.test(value)) {
                return false;
            }
        }
        return true;
    }
}
