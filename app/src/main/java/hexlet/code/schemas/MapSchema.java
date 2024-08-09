package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<String, String>> {

    @Override
    public MapSchema required() {
        checks.put("required", map -> map.values().stream().allMatch(Objects::nonNull));
        return this;
    }

    public MapSchema sizeof(int size) {
        checks.put("sizeof", map -> map.size() == size);
        return this;
    }

    @Override
    public boolean isValid(Map<String, String> value) {
        return super.isValid(value);
    }
}
