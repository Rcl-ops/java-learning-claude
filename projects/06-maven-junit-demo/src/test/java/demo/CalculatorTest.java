package demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();               // 每个测试都用全新的对象，互不影响
    }

    @Test
    @DisplayName("加法：正常情况")
    void addNormal() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void addOverflowThrows() {
        assertThrows(ArithmeticException.class, () -> calc.add(Integer.MAX_VALUE, 1));
    }

    @Test
    void divideByZeroThrows() {
        var ex = assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
        assertEquals("除数不能为 0", ex.getMessage());
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({"10, 2, 5", "9, 3, 3", "-8, 2, -4", "7, 2, 3"})
    void divideMany(int a, int b, int expected) {
        assertEquals(expected, calc.divide(a, b));
    }

    @Test
    void factorial() {
        assertAll(
                () -> assertEquals(1, calc.factorial(0)),
                () -> assertEquals(120, calc.factorial(5)),
                () -> assertEquals(2432902008176640000L, calc.factorial(20)),
                () -> assertThrows(ArithmeticException.class, () -> calc.factorial(21)));
    }
}
