package unitTests.thingsToSearch;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.javafaker.Faker;

import thingsToSearch.UnaryOperatorCounter;

public class UnaryOperatorCounterTest{
private UnaryOperatorCounter processor;
private Map<String, Integer> counts;

@BeforeEach
void setUp() {
    processor = new UnaryOperatorCounter();
    counts = new HashMap<>();
}

@ParameterizedTest
@CsvSource({ "+a,1", "-b,1", "++c,1", "--d,1", "e++,1", "f--,1", "+ +g,1", "- -h,1", "++i,1", "- -j,1",
        "k+ +,1", "l- -,1", "m + +,1", "n - -,1", "o+ +p,2","s++ +,1", "t-- -,1", "+u+v,1", "-w-x,1",
        "+ +y+ +,2", "- -z- -,2" })
void testCountUnaryOperators(String codeLine, int expectedCount) {
    processor.processCode(codeLine, counts);
    assertEquals(expectedCount, processor.getUnaryOperatorCount());
}

@ParameterizedTest
@CsvSource({ "// a comment", "System.out.println(\"a string literal\");",
        "int x = 10; // a comment with a unary operator y=a+x" })
void testProcessCodeIgnoresCommentsAndStringLiterals(String codeLine) {
    processor.processCode(codeLine, counts);
    assertEquals(0, processor.getUnaryOperatorCount());
}

@ParameterizedTest
@ValueSource(strings = { "'a'", "'\\''", "'\\\\'", "'\n'" })
void testProcessCodeIgnoresCharacterLiterals(String codeLine) {
    processor.processCode(codeLine, counts);
    assertEquals(0, processor.getUnaryOperatorCount());
}

}