package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SchemasTest {
    Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void testStringSchema() {
        StringSchema schema = validator.string();

        // Пока не вызван метод required(), null и пустая строка считаются валидными
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();

        schema.required();

        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();

        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        assertThat(schema.isValid("what does the fox say")).isFalse();
        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

        // Если один валидатор вызывался несколько раз,
        // то последний имеет приоритет (перетирает предыдущий)
        StringSchema schema1 = validator.string();
        assertThat(schema1.minLength(10).minLength(4).isValid("Hexlet")).isTrue();
    }

    @Test
    void testNumberSchema() {
        NumberSchema schema = validator.number();

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(-0)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        // Пока не вызван метод required(), null считается валидным
        assertThat(schema.positive().isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();

        // Потому что ранее мы вызвали метод positive()
        assertThat(schema.isValid(-10)).isFalse();
        //  Ноль — не положительное число
        assertThat(schema.isValid(0)).isFalse();

        schema.range(5, 10); // Проверка диапазона

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

//    @Test
//    void testMapSchema() {
//        MapSchema schema = validator.map();
//
//        assertThat(schema.isValid(null)).isTrue();
//        assertThat(schema.isValid(new Object())).isFalse();
//        assertThat(schema.isValid(new Object[]{})).isFalse();
//        assertThat(schema.isValid(new Object[]{1, 2, 3})).isFalse();
//        assertThat(schema.isValid(new Object[]{"a", "b", "c"})).isFalse();
//        assertThat(schema.isValid(new Object[]{"a", 1, "c"})).isFalse();
//    }
}
