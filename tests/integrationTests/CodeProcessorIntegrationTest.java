package integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import analyzer.CodeAnalyzer;
import fileOperations.abstracts.ICodeProcessor;
import fileOperations.abstracts.ICommentProcessor;
import fileOperations.concretes.CodeProcessor;
import fileOperations.concretes.CommentProcessor;
import thingsToSearch.BinaryOperatorCounter;
import thingsToSearch.FunctionCounter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodeProcessorIntegrationTest {
	@Test
	public void testCodeProcessor() throws IOException {
	  //Inside this i dont create mocks we are creating real objects to see if they can interact each other succesfully
	    File tempFile = File.createTempFile("sample-code", ".java");
	    String code = "public class Test {\n" +
	            "// This is a comment\n" +
	            "int x = 5;\n" +
	            "/* This is a\n" +
	            "multiline comment */\n" +
	            "int y = 10;\n" +
	            "}";
	    Files.write(tempFile.toPath(), code.getBytes());

	    ICommentProcessor commentProcessor = new CommentProcessor();
	    ICodeProcessor binaryOperatorCounter = new BinaryOperatorCounter();
	    ICodeProcessor functionCounter = new FunctionCounter();

	    CodeAnalyzer codeAnalyzer = new CodeAnalyzer(Arrays.asList(binaryOperatorCounter, functionCounter));

	    CodeProcessor codeProcessor = new CodeProcessor(commentProcessor, codeAnalyzer);
	    codeProcessor.processCode(tempFile.getAbsolutePath());

	  	assertEquals(1, codeAnalyzer.getResults().size());

	    
	    assertEquals(1, codeAnalyzer.getBinaryCount());
	    assertEquals(0, codeAnalyzer.getFunctionCount());
	    tempFile.delete();
	}

}
