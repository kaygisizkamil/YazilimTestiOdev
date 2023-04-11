package integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import analyzer.CodeAnalyzer;
import fileOperations.abstracts.ICodeProcessor;
import fileOperations.abstracts.ICommentProcessor;
import fileOperations.concretes.CodeProcessor;
import fileOperations.concretes.CommentProcessor;
import thingsToSearch.BinaryOperatorCounter;
import thingsToSearch.FunctionCounter;
import thingsToSearch.LogicalOperatorCounter;
import thingsToSearch.OperandCounter;
import thingsToSearch.RelationalOperatorCounter;
import thingsToSearch.UnaryOperatorCounter;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryIntegrationTest {

    private String filePath;

    @BeforeEach
    public void setup() throws IOException {
        // Create the test file
        File file = File.createTempFile("temp", ".java");
        filePath = file.getAbsolutePath();
        String code = "public class Test {\n" +
                "// This is a comment\n" +
                "int x = 5;\n" +
                "/* This is a\n" +
                "multiline comment */\n" +
                "int y = 10;\n" +
                "}";
        Files.write(file.toPath(), code.getBytes());
    }

    @AfterEach
    public void cleanup() {
        // Delete the test file
        File file = new File(filePath);
        file.delete();
    }

    @Test
    public void testValidCode() throws IOException {

    
        ICommentProcessor commentProcessor = new CommentProcessor();
        List<ICodeProcessor> codeProcessors = Arrays.asList(new LogicalOperatorCounter(), new RelationalOperatorCounter(), new OperandCounter(), new UnaryOperatorCounter(), new BinaryOperatorCounter(), new FunctionCounter());

        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(codeProcessors);
        CodeProcessor codeProcessor = new CodeProcessor(commentProcessor, codeAnalyzer);

        codeProcessor.processCode(filePath);

        assertEquals(0, codeAnalyzer.getUnaryOperatorCount());
        assertEquals(1, codeAnalyzer.getBinaryCount());
        assertEquals(2, codeAnalyzer.getOperandCount());
        assertEquals(0, codeAnalyzer.getFunctionCount());
    }

  /*  @Test
    public void testMalformedCode() throws IOException {
        // Modify the test file to include a syntax error
        String code = "public class Test {\n" +
                "int x = 5\n" +  // Missing semicolon
                "int y = 10;\n" +
                "}";
        Files.write(new File(filePath).toPath(), code.getBytes());

        ICommentProcessor commentProcessor = new CommentProcessor();
        List<ICodeProcessor> codeProcessors = Arrays.asList(new LogicalOperatorCounter(), new RelationalOperatorCounter(), new OperandCounter(), new UnaryOperatorCounter(), new BinaryOperatorCounter(), new FunctionCounter());

        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(codeProcessors);
        CodeProcessor codeProcessor = new CodeProcessor(commentProcessor, codeAnalyzer);

        // Verify that an exception is thrown when processing the code
        assertThrows(Exception.class, () -> codeProcessor.processCode(filePath));
    }*/

    @Test
    public void testEmptyCode() throws IOException {
        String code = "";
        Files.write(new File(filePath).toPath(), code.getBytes());

        ICommentProcessor commentProcessor = new CommentProcessor();
        List<ICodeProcessor> codeProcessors = Arrays.asList(new LogicalOperatorCounter(), new RelationalOperatorCounter(), new OperandCounter(), new UnaryOperatorCounter(), new BinaryOperatorCounter(), new FunctionCounter());

        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(codeProcessors);
        CodeProcessor codeProcessor = new CodeProcessor(commentProcessor, codeAnalyzer);

        codeProcessor.processCode(filePath);
        assertTrue(codeAnalyzer.getResults().isEmpty());
    }

    @Test
    public void testNonExistentCode() throws IOException {
        File file = new File(filePath);
       
}}