package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        checks.put("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        checks.put("minLength", value -> value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.put("contains", value -> value.contains(substring));
        return this;
    }
}
