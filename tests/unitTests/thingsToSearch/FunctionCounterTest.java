package unitTests.thingsToSearch;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import thingsToSearch.FunctionCounter;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FunctionCounterTest {

    @ParameterizedTest
    @CsvSource({"'', 0", "'public void testFunction() {', 1", "'private static int count = 0;', 0",
            "'public class MyClass {', 0", "'void doSomething(String arg) {', 1", "'    int x = 0;', 0",
            "'public String toString() {', 1"})
    void testProcessCode(String codeLine, int expectedFunctionCount) {
        Map<String, Integer> counts = new HashMap<>();
        FunctionCounter functionCounter = new FunctionCounter();
        functionCounter.processCode(codeLine, counts);
        assertEquals(expectedFunctionCount, functionCounter.getFunctionCount(),
                "Incorrect function count for code line: " + codeLine);
    }

    @Test
    void testGetCodeElement() {
        FunctionCounter functionCounter = new FunctionCounter();
        assertEquals("function", functionCounter.getCodeElement(),
                "getCodeElement() should return 'function'");
    }
}






