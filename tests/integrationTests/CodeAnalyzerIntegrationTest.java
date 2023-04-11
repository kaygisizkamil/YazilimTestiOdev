package integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import analyzer.CodeAnalyzer;
import fileOperations.abstracts.ICodeProcessor;
import thingsToSearch.BinaryOperatorCounter;
import thingsToSearch.FunctionCounter;
import thingsToSearch.LogicalOperatorCounter;
import thingsToSearch.OperandCounter;
import thingsToSearch.RelationalOperatorCounter;
import thingsToSearch.UnaryOperatorCounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeAnalyzerIntegrationTest {

    @Test
    public void testAnalyze() {
        List<ICodeProcessor> processors = new ArrayList<>();
        processors.add(new BinaryOperatorCounter());
        processors.add(new FunctionCounter());
        processors.add(new UnaryOperatorCounter());
        processors.add(new LogicalOperatorCounter());
        processors.add(new RelationalOperatorCounter());
        processors.add(new OperandCounter());
        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(processors);

        List<String> codeLines = new ArrayList<>();
        codeLines.add("int a = 5;");
        codeLines.add("int b = 10;");
        codeLines.add("int c = a + b;");
        codeLines.add("if (c > 15) {");
        codeLines.add("    System.out.println(\"Greater than 15\");");
        codeLines.add("} else {");
        codeLines.add("    System.out.println(\"Less than or equal to 15\");");
        codeLines.add("}");
        codeLines.add("for (int i = 0; i < 10; i++) {");
        codeLines.add("    System.out.println(i);");
        codeLines.add("}");

        codeAnalyzer.analyze(codeLines);

        assertEquals(4, codeAnalyzer.getBinaryCount());
        assertEquals(0, codeAnalyzer.getFunctionCount());
        assertEquals(1, codeAnalyzer.getUnaryOperatorCount());
        assertEquals(0, codeAnalyzer.getLogicalOperatorCount());
        assertEquals(2, codeAnalyzer.getRelationalCount());
        assertEquals(6, codeAnalyzer.getOperandCount());
    }

    @Test
    public void testGetArithmetic() {
        List<ICodeProcessor> processors = new ArrayList<>();
        processors.add(new BinaryOperatorCounter());
        processors.add(new UnaryOperatorCounter());

        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(processors);

        List<String> codeLines = new ArrayList<>();
        codeLines.add("int a = 5;");
        codeLines.add("int b = 10;");
        codeLines.add("int c = a + b;");
        codeLines.add("if (c > 15) {");
        codeLines.add("    System.out.println(\"Greater than 15\");");
        codeLines.add("} else {");
        codeLines.add("    System.out.println(\"Less than or equal to 15\");");
        codeLines.add("}");
        codeLines.add("for (int i = 0; i < 10; i++) {");
        codeLines.add("    System.out.println(i);");
        codeLines.add("}");

        codeAnalyzer.analyze(codeLines);

        assertEquals(5, codeAnalyzer.getArithmetic());
    }
}
