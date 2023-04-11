package integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

class CompleteUseTest {

	@Test
	void test() throws IOException {
		   String filePath = "tests/resources/Deneme.java";
	        ICommentProcessor commentProcessor = new CommentProcessor();
	        List<ICodeProcessor> codeProcessors = Arrays.asList(new LogicalOperatorCounter(),new RelationalOperatorCounter(),new OperandCounter(),new UnaryOperatorCounter(),new BinaryOperatorCounter(),new FunctionCounter());

	        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(codeProcessors);
	        CodeProcessor codeProcessor = new CodeProcessor(commentProcessor, codeAnalyzer);

	        codeProcessor.processCode(filePath);
	        assertEquals(1, codeAnalyzer.getUnaryOperatorCount());
	        assertEquals(5, codeAnalyzer.getBinaryCount());
	        assertEquals(3, codeAnalyzer.getRelationalCount());
	        assertEquals(2,codeAnalyzer.getLogicalOperatorCount()); 
	        assertEquals(3,codeAnalyzer.getFunctionCount());
	        assertEquals(11, codeAnalyzer.getOperandCount());

	        System.out.println(codeAnalyzer.getUnaryOperatorCount());
	        System.out.println(codeAnalyzer.getBinaryCount());
	        System.out.println(codeAnalyzer.getRelationalCount());
	        System.out.println(codeAnalyzer.getLogicalOperatorCount());
	        System.out.println(codeAnalyzer.getFunctionCount());
	        System.out.println(codeAnalyzer.getOperandCount());
	}

}
