/**
*
* @author Kamil Kaygısız kamil.kaygisiz1@ogr.sakarya.edu.tr
* @since 03.04.2023
* <p>
* Bu sınıf icinde butun analiz metodlarına ulaşılabilir gerekli sayıları return etmeyi sağlar
* </p>
*/

package analyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fileOperations.abstracts.ICodeProcessor;
import requiredJars.JarsChecker;
import thingsToSearch.BinaryOperatorCounter;
import thingsToSearch.FunctionCounter;
import thingsToSearch.LogicalOperatorCounter;
import thingsToSearch.OperandCounter;
import thingsToSearch.RelationalOperatorCounter;
import thingsToSearch.UnaryOperatorCounter;

public class CodeAnalyzer {
	    private List<ICodeProcessor> codeProcessors;
	    private Map<String, Integer> results;


	    public CodeAnalyzer(List<ICodeProcessor> codeProcessors) {
	        this.codeProcessors = codeProcessors;
	        this.results=new HashMap<>();
	    }

	    public void analyze(List<String> codeLines) {
	        Map<String, Integer> counts = new HashMap<>();
	        //will return general results like function=5 unaryOp=6 etc
	        for (String line : codeLines) {
	            for (ICodeProcessor codeProcessor : codeProcessors) {
	                codeProcessor.processCode(line, counts);
	            }
	        }
	        //for more detailed results inside integration test is will be used
	        for (String codeElement : counts.keySet()) {
	            if (results.containsKey(codeElement)) {
	                results.put(codeElement, results.get(codeElement) + counts.get(codeElement));
	            } else {
	                results.put(codeElement, counts.get(codeElement));
	            }
	        }

	     //   System.out.println(   printCounts(counts));
	    }

	    public int getArithmetic() {
	        int total = 0;

	        return getBinaryCount()+getUnaryOperatorCount();/// total;

	    }
	    public int getBinaryCount(){
	            int total=0;
	            for (ICodeProcessor processor : codeProcessors) {
	                if (processor instanceof BinaryOperatorCounter) {
	                    total += ((BinaryOperatorCounter) processor).getBinaryCount();

	                }
	            }
	            return total;
	        }


	    public int getFunctionCount(){
	        int total=0;
	        for (ICodeProcessor processor : codeProcessors) {
	            if (processor instanceof FunctionCounter) {
	                total += ((FunctionCounter) processor).getFunctionCount();
	            }
	        }
	        return total;
	    }
	    public int getUnaryOperatorCount() {
	        int total = 0;
	        for (ICodeProcessor processor : codeProcessors) {
	            if (processor instanceof UnaryOperatorCounter) {
	                total += ((UnaryOperatorCounter) processor).getUnaryOperatorCount();
	            }
	        }
	        return total;
	    }
	    public int getLogicalOperatorCount() {
	        int total = 0;
	        for (ICodeProcessor processor : codeProcessors) {
	            if (processor instanceof LogicalOperatorCounter) {
	                total += ((LogicalOperatorCounter) processor).getLogicalOperatorCount();
	            }
	        }
	        return total;
	    }
	    public int getRelationalCount(){
	        int total=0;
	        for(ICodeProcessor processor:codeProcessors){
	            if(processor instanceof RelationalOperatorCounter){
	                total+=((RelationalOperatorCounter)processor).getRelationalCount();
	            }
	        }
	        return  total;
	    }

	    public int getOperandCount(){
	        int total=0;
	        for(ICodeProcessor processor:codeProcessors){
	            if(processor instanceof OperandCounter){
	                total+=((OperandCounter)processor).getOperandCount();
	            }
	        }
	        return  total;
	    }
	    public Map<String, Integer> getResults() {
	        return results;
	    }

	   /* private int printCounts(Map<String, Integer> counts) {
	        for (String codeElement : counts.keySet()) {
	            System.out.println(codeElement + ": " + counts.get(codeElement));

	        }
	        int count=0;
	        for(int i:counts.values()){
	            count+=i;
	        }
	        return count;
	    }*/
	
}