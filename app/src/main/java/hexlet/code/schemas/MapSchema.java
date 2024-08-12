package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    @Override
    public MapSchema required() {
        checks.put("required", map -> map.values().stream().allMatch(Objects::nonNull));
        return this;
    }

    public void sizeof(int size) {
        checks.put("sizeof", map -> map.size() == size);
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        checks.put("shape", map -> map.entrySet().stream()
                .allMatch(entry -> schemas.get(entry.getKey()).isValid((String) entry.getValue())));
    }
}
