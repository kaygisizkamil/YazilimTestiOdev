package unitTests.thingsToSearch;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import thingsToSearch.LogicalOperatorCounter;


public class LogicalOperatorCounterTest {

    @Nested
    @DisplayName("Test processCode method")
    class ProcessCodeTests {
        @ParameterizedTest(name = "Test #{index}: codeLine={0}, expectedCount={1}")
        @CsvSource({
                "x = y && z, 1",
                "if (a || b) {, 1",
                "! flag, 1",
                "x = y & z, 0",
                "if (a | b) {, 0",
                "if (a && b || c && d) {, 3",
                "if (! (a && b)) {, 2",
                "if ((a && b) || (c && d)) {, 3",
                "if (a && b) { if (c || d) {, 2"
        })
        void testProcessCode(String codeLine, int expectedCount) {
        
            LogicalOperatorCounter operatorCounter = new LogicalOperatorCounter();
            Map<String, Integer> counts = new HashMap<>();
            operatorCounter.processCode(codeLine, counts);
            Assertions.assertEquals(expectedCount, operatorCounter.logicalOperatorCount(),
                    "Incorrect logical operator count for code line: " + codeLine);
        }
    }

    @RepeatedTest(5)
    void testRepeatedProcessCode(RepetitionInfo repetitionInfo) {
        LogicalOperatorCounter operatorCounter = new LogicalOperatorCounter();
        Map<String, Integer> counts = new HashMap<>();
        String codeLine = "";
        int expectedCount = 0;

        switch (repetitionInfo.getCurrentRepetition()) {
            case 1:
                codeLine = "x = y && z";
                expectedCount = 1;
                break;
            case 2:
                codeLine = "if (a || b) {";
                expectedCount = 1;
                break;
            case 3:
                codeLine = "! flag";
                expectedCount = 1;
                break;
            case 4:
                codeLine = "x = y & z";
                expectedCount = 0;
                break;
            case 5:
                codeLine = "if (a | b) {";
                expectedCount = 0;
                break;
        }

        operatorCounter.processCode(codeLine, counts);
        Assertions.assertEquals(expectedCount, operatorCounter.logicalOperatorCount(),
                "Incorrect logical operator count for code line: " + codeLine);
    }
}