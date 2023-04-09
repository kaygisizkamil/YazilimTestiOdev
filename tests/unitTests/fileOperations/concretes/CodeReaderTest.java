package unitTests.fileOperations.concretes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import fileOperations.abstracts.ICommentProcessor;
import fileOperations.concretes.CodeReader;
class CodeReaderTest {
	   @Mock//we are not doing an integration test in here since it is unit test we can mock this instead
	   ICommentProcessor mockCommentProcessor;
	    private CodeReader codeReader;
	    private Path testFilePath;

	    @BeforeEach
	    void setUp() throws IOException {
	    	mockCommentProcessor = mock(ICommentProcessor.class);
	        // Create a test file before each test
	        testFilePath = Files.createTempFile(null, null);
	        Files.write(testFilePath, Arrays.asList("line 1", "line 2", "line 3"));
	        codeReader = new CodeReader(testFilePath.toString());
	    }

	    @AfterEach
	    void tearDown() throws IOException {
	        Files.deleteIfExists(testFilePath);
	    }

	    @Test
	    void testReadCode() throws IOException {
	    	when(mockCommentProcessor.processLineDeleteComments(anyString()))
	    		.thenAnswer(invocation->invocation.getArgument(0));
	        /*when it is called with any input string 
	         * it is returning the argument that
	         *  was passed into the method (invocation.getArgument(0)) 
	         *  which is equivalent to the input string*/
	        // Read the code from the file using the CodeReader instance and the mock comment processor
	        List<String> codeLines = codeReader.readCode(mockCommentProcessor);
	        assertEquals(Arrays.asList("line 1", "line 2", "line 3"), codeLines);
	}

}