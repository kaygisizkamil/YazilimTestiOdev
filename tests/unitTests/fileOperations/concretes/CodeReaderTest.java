package unitTests.fileOperations.concretes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import fileOperations.abstracts.ICommentProcessor;
import fileOperations.concretes.CodeProcessor;
import fileOperations.concretes.CodeReader;
import fileOperations.concretes.CommentProcessor;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import analyzer.CodeAnalyzer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.Mockito.*;
public class CodeReaderTest {

    private CodeProcessor codeProcessor;
    String filePath;

    @Mock
    private ICommentProcessor commentProcessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        filePath = "tests/resources/Deneme.java";
    }

    @Test
    public void testReadFile() throws IOException {
        CodeReader codeReader = new CodeReader(filePath);
        List<String> codeLines = codeReader.readCode(new CommentProcessor());
/*
 * package resources;
public class Deneme {
public int x;
public int y;
public Deneme(int x,int y) {
this.x = x;
this.y = y;
}*/
        assertEquals(35, codeLines.size());
        assertEquals("public class Deneme {", codeLines.get(1));
        assertEquals("public int x;", codeLines.get(2));
        assertEquals("public Deneme(int x,int y) {", codeLines.get(4));
        // ...
        assertEquals("}", codeLines.get(34));
    }

   
}