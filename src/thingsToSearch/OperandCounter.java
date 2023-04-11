package thingsToSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileOperations.abstracts.ICodeProcessor;

public class OperandCounter implements ICodeProcessor {

    private static final Pattern PATTERN = Pattern.compile("(?<!&)&(?!=)|(?<!\\|)\\|(?!=)|[\\+\\-*/%&|^]=|[\\+\\-*/%&|^]{1,2}(?!=)|(?<![!=<>&|^])=(?![=])|(?<=[+*/%&|^])-{1}(?![=-])|(?<=\\*)\\*(?![=])|(?<=/)/(?![=])|(?<!&)&=(?![=])|(?<=\\|)\\|=(?![=])");
    private final List<String> operands = new ArrayList<>();
    @Override
    public void processCode(String codeLine, Map<String, Integer> counts) {
        Matcher matcher = PATTERN.matcher(codeLine);
        while (matcher.find()) {
            String operand = matcher.group();
            operands.add(operand);
            counts.put(operand, counts.getOrDefault(operand, 0) + 1);
        }
    }

    @Override
    public String getCodeElement() {
        return "Mathematical Operands";
    }

    public int getOperandCount() {
        return operands.size();
    }
}
