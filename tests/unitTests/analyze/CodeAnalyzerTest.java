package unitTests.analyze;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import analyzer.CodeAnalyzer;
import fileOperations.abstracts.ICodeProcessor;

public class CodeAnalyzerTest {//inside another branch
	 private CodeAnalyzer codeAnalyzer;
	 private ICodeProcessor mockProcessor1;
	 private ICodeProcessor mockProcessor2;
    @BeforeEach
    public void setUp() {
        mockProcessor1 = mock(ICodeProcessor.class);
        mockProcessor2 = mock(ICodeProcessor.class);
        codeAnalyzer = new CodeAnalyzer(Arrays.asList(mockProcessor1, mockProcessor2));
    }
    @Test
    public void testAnalyze() {
        // Arrange tthe list
        List<ICodeProcessor> codeProcessors = new ArrayList<>();
        ICodeProcessor mockCodeProcessor1 = Mockito.mock(ICodeProcessor.class);
        ICodeProcessor mockCodeProcessor2 = Mockito.mock(ICodeProcessor.class);
        codeProcessors.add(mockCodeProcessor1);
        codeProcessors.add(mockCodeProcessor2);

        CodeAnalyzer codeAnalyzer = new CodeAnalyzer(codeProcessors);

        List<String> codeLines = new ArrayList<>();
        codeLines.add("int x = 10;");
        codeLines.add("int y = 20;");
        codeLines.add("int z = x + y;");     
        codeAnalyzer.analyze(codeLines);

        verifyCodeProcessorsWereCalledWithExpectedCounts(codeProcessors, codeLines.size());
    }

    private void verifyCodeProcessorsWereCalledWithExpectedCounts(List<ICodeProcessor> codeProcessors, int expectedCallCount) {
        for (ICodeProcessor codeProcessor : codeProcessors) {
            verify(codeProcessor, times(expectedCallCount)).processCode(anyString(), anyMap());
        }
    }
}