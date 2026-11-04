import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void additionWorks() {
        assertEquals("5", Calculator.answer("ADD 2 3"));
    }

    @Test
    void multiplicationWorks() {
        assertEquals("20", Calculator.answer("MUL 4 5"));
    }

    @Test
    void additionWithDifferentNumbers() {
        assertEquals("25", Calculator.answer("ADD 10 15"));
    }

    @Test
    void missingNumberReturnsError() {
        assertTrue(Calculator.answer("ADD 2").startsWith("ERROR"));
    }
}
