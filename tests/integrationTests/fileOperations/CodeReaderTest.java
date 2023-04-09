package integrationTests.fileOperations;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fileOperations.concretes.CodeReader;
import fileOperations.concretes.CommentProcessor;

class CodeReaderTest {
    private CodeReader codeReader;
    private Path testFilePath;

    @BeforeEach
    void setUp() throws IOException {
        testFilePath = Files.createTempFile(null, null);
        codeReader = new CodeReader(testFilePath.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(testFilePath);
    }

    @Test
    void testReadCodeWithNoComments() throws IOException {
        List<String> inputLines = Arrays.asList("line 1", "line 2", "line 3");
        Files.write(testFilePath, inputLines);

        List<String> codeLines = codeReader.readCode(new CommentProcessor());

        assertEquals(inputLines, codeLines);
    }

    @Test
    void testReadCodeWithSingleLineComment() throws IOException {
        List<String> inputLines = Arrays.asList("line 1", "//comment", "line 3");
        Files.write(testFilePath, inputLines);

        List<String> expectedCodeLines = Arrays.asList("line 1", "", "line 3");

        List<String> codeLines = codeReader.readCode(new CommentProcessor());

        assertEquals(expectedCodeLines, codeLines);
    }

    @Test
    void testReadCodeWithMultiLineComment() throws IOException {
        List<String> inputLines = Arrays.asList("line 1", "/*", "comment", "comment", "*/", "line 3");
        Files.write(testFilePath, inputLines);

        List<String> expectedCodeLines = Arrays.asList("line 1", "", "", "", "", "line 3");

        List<String> codeLines = codeReader.readCode(new CommentProcessor());

        assertEquals(expectedCodeLines, codeLines);
    }

    @Test
    void testReadCodeWithMixedComments() throws IOException {
        List<String> inputLines = Arrays.asList("line 1", "//comment", "line 2", "/*", "comment", "*/", "line 3");
        Files.write(testFilePath, inputLines);

        List<String> expectedCodeLines = Arrays.asList("line 1", "", "line 2", "", "", "", "line 3");

        List<String> codeLines = codeReader.readCode(new CommentProcessor());

        assertEquals(expectedCodeLines, codeLines);
    }
}
