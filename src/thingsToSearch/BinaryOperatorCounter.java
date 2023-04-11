package thingsToSearch;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileOperations.abstracts.ICodeProcessor;

public class BinaryOperatorCounter  implements ICodeProcessor {
	/* it matches with Arithmetic operators: +, -, *, /, %
Bitwise operators: &, |, ^, ~
Assignment operators: =*/
    private static final String BINARY_OPERATORS_REGEX = "(?<![\\w])[-+*/%&|^=<>]{1,2}(?![\\w=])";

    private int count =0;

    @Override
    public void processCode(String codeLine, Map<String, Integer> counts) {
        Pattern pattern = Pattern.compile(BINARY_OPERATORS_REGEX);
        Matcher matcher = pattern.matcher(codeLine);

        while (matcher.find()) {
            count++;
            String operator = matcher.group();
            counts.put(operator, counts.getOrDefault(operator, 0) + 1);
        }

    }
  // best i can write was this it matches with pairs so i needed return like this
    public int getBinaryCount(){
        return count/2+1;
    }

    @Override
    public String getCodeElement() {
        return "Binary Operators";
    }
}