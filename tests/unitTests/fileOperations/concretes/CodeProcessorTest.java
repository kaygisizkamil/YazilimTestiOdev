package unitTests.fileOperations.concretes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import analyzer.CodeAnalyzer;
import fileOperations.abstracts.ICommentProcessor;
import fileOperations.concretes.CodeProcessor;
import fileOperations.concretes.CodeReader;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;


    public class CodeProcessorTest {

        private CodeProcessor codeProcessor;

        @Mock
        private ICommentProcessor commentProcessor;

        @Mock
        private CodeAnalyzer codeAnalyzer;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            codeProcessor = new CodeProcessor(commentProcessor, codeAnalyzer);
        }

        @Test
        public void testProcessCode() throws IOException {
            String filePath = "tests/resources/Deneme.java";
            List<String> codeLines = Files.readAllLines(Paths.get(filePath));

            when(commentProcessor.processLineDeleteComments(anyString()))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            doNothing().when(codeAnalyzer).analyze(codeLines);

            codeProcessor.processCode(filePath);
            //since we are testing unit hardcoding is acceptable maybe advisable at some level
            //the resource has 35 line so it makes sense to test it like this
            verify(commentProcessor, times(35)).processLineDeleteComments(anyString());
            verify(codeAnalyzer, times(1)).analyze(codeLines);
        }




    }


