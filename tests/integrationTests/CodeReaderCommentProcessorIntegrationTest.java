package integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fileOperations.abstracts.ICommentProcessor;
import fileOperations.concretes.CodeReader;
import fileOperations.concretes.CommentProcessor;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
public class CodeReaderCommentProcessorIntegrationTest {

    @Test
    public void testReadCodeWithComments() throws IOException {
        File tempFile = File.createTempFile("test-file", ".java");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("int x = 1; // This is a comment\n" +
                "int y = 2; // This is another comment\n" +
                "System.out.println(x + y);");
        writer.close();

        // Read the code from the temporary file
        ICommentProcessor commentProcessor = new CommentProcessor();
        CodeReader codeReader = new CodeReader(tempFile.getAbsolutePath());
        List<String> actualCodeLines = codeReader.readCode(commentProcessor);

        // Assert that the code lines are read correctly
        List<String> expectedCodeLines = Arrays.asList("int x = 1; ", "int y = 2; ", "System.out.println(x + y);");
        Assertions.assertEquals(expectedCodeLines, actualCodeLines);

        tempFile.delete();
    }

    @Test
    void testEmptyFile() throws IOException {
        File tempFile = File.createTempFile("test-file", ".java");

        ICommentProcessor commentProcessor = new CommentProcessor();
        CodeReader codeReader = new CodeReader(tempFile.getAbsolutePath());
        List<String> actualCodeLines = codeReader.readCode(commentProcessor);

        // Assert that the result is an empty list
        Assertions.assertTrue(actualCodeLines.isEmpty());

        tempFile.delete();
    }

    @Test
    public void testReadNonExistentFile() {
        ICommentProcessor commentProcessor = new CommentProcessor();
        CodeReader codeReader = new CodeReader("invalid_file_path.txt");
        Assertions.assertThrows(IOException.class, () -> codeReader.readCode(commentProcessor));
    }
}

