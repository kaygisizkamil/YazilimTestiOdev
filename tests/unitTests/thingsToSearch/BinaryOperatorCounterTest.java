package unitTests.thingsToSearch;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import thingsToSearch.BinaryOperatorCounter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryOperatorCounterTest {

    @ParameterizedTest
    @CsvSource({
            "int a = b + c;, +, 1",
            "int x = y * z;, *, 1",
            "if (a > b) { a = b; }, >, 1",
            "a | = b;, |=, 1",
            "int a = b ++ c;, , 1",
            "a && b;, , 0",
            "a || b;, , 0",
            "-2 + 5 * 3;, +, 1",
            "a & b | c ^ d;, &, 1",
            "x = y + (z - w);, +, 1",
           
    })
    void testProcessCode(String codeLine, String operator, int expectedCount) {
        BinaryOperatorCounter counter = new BinaryOperatorCounter();
        Map<String, Integer> counts = new HashMap<>();
        counter.processCode(codeLine, counts);
        assertEquals(expectedCount, counter.getBinaryCount(),
                "Incorrect binary operator count for code line: " + codeLine);
    }

    @RepeatedTest(4)
    void testRepeatedProcessCode(RepetitionInfo repetitionInfo) {
        BinaryOperatorCounter counter = new BinaryOperatorCounter();
        Map<String, Integer> counts = new HashMap<>();
        String codeLine = "";
        String operator = "";
        int expectedCount = 0;

        switch (repetitionInfo.getCurrentRepetition()) {
            case 1:
                codeLine = "int a = b + c;";
                operator = "+";
                expectedCount = 1;
                break;
            case 2:
                codeLine = "int x = y * z;";
                operator = "*";
                expectedCount = 1;
                break;
            case 3:
                codeLine = "if (a > b) { a = b; }";
                operator = ">";
                expectedCount = 1;
                break;
            case 5:
                codeLine = "int a = b + + c;";
                operator = "";
                expectedCount = 0;
                break;
        }

        counter.processCode(codeLine, counts);
        assertEquals(expectedCount, counter.getBinaryCount(),
                "Incorrect binary operator count for code line: " + codeLine);
    }

   
}

