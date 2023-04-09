package analyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fileOperations.abstracts.ICodeProcessor;

public class CodeAnalyzer {
    private List<ICodeProcessor> codeProcessors;

    public CodeAnalyzer(List<ICodeProcessor> codeProcessors) {
        this.codeProcessors = codeProcessors;
    }

    public void analyze(List<String> codeLines) {
    	Map<String,Integer>counts=new HashMap<>();
        for (String line : codeLines) {
            for (ICodeProcessor codeProcessor : codeProcessors) {
                codeProcessor.processCode(line, counts);
            }
        }
     //   System.out.println(   printCounts(counts));
    }
    private int printCounts(Map<String, Integer> counts) {
        for (String codeElement : counts.keySet()) {
            System.out.println(codeElement + ": " + counts.get(codeElement));

        }
        int count=0;
        for(int i:counts.values()){
            count+=i;
        }
        return count;
    }
}