package unitTests.fileOperations.concretes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fileOperations.concretes.CommentProcessor;

public class CommentProcessorTest {
	 private CommentProcessor commentProcessor;
	    @BeforeEach
	    public void setup() {
	        commentProcessor = new CommentProcessor();
	    }

   @Test
   public void testProcessLineDeleteComments_singleLineComment() {
   	String input = "int a = 5;// This is a comment";
       String expectedOutput = "int a = 5;";
       String actualOutput = commentProcessor.processLineDeleteComments(input);
        assertEquals(expectedOutput,actualOutput);
   }

   @Test
   public void testProcessLineDeleteComments_multiLineComment() {
       String input = "int c = 4; /* This is a comment */int b = 6;";
       String expectedOutput = "int b = 6;";
       String actualOutput = commentProcessor.processLineDeleteComments(input);
       assertEquals(expectedOutput, actualOutput);
   }

   @Test
   public void testProcessLineDeleteComments_noComment() {
       String input = "int a = 5; int b = 6;";
       String expectedOutput = "int a = 5; int b = 6;";
       String actualOutput = commentProcessor.processLineDeleteComments(input);
       assertEquals(expectedOutput, actualOutput);
   }
}