package unitTests.thingsToSearch;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import thingsToSearch.RelationalOperatorCounter;

@DisplayName("RelationalOperatorCounter")
class RelationalOperatorCounterTest {

    @ParameterizedTest(name = "should count {0} relational operator(s) in \"{1}\"")
    @CsvSource({
            "0, int x = 1",
            "1, if (x < 0) {",
            "2, if (y >= 10 && z == 5) {"
    })
    void shouldCountRelationalOperators(int expectedCount, String codeLine) {
        Map<String, Integer> counts = new HashMap<>();
        new RelationalOperatorCounter().processCode(codeLine, counts);
        assertEquals(expectedCount, counts.values().stream().mapToInt(Integer::intValue).sum());
    }

 
    @Test
    @DisplayName("should count all relational operators")
    void shouldCountAllRelationalOperators() throws IOException {
        Path filePath = Path.of("tests/resources/Deneme.java");
        RelationalOperatorCounter counter = new RelationalOperatorCounter();
        Map<String, Integer> counts = new HashMap<>();
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> counter.processCode(line, counts));
        }
     /*   System.out.println("Relational count: " + counter.getRelationalCount());
        System.out.println("Counts: " + counts);*/
        //since we are not testing the commentprocessor in here the expected behaviour is also counting 
        //relational operators inside comments
        //comment processoru unit test içinde denemiyoruz operator yorum icinde bile olsa sayilmali
        assertEquals(5, counter.getRelationalCount());
        assertEquals(1, counts.get("!=").intValue());
        assertEquals(2, counts.get("==").intValue());
        assertEquals(2, counts.get("==").intValue());
        assertEquals(2, counts.get(">").intValue());
    }


}