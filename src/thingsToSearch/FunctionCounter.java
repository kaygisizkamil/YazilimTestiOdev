/**
*
* @author Kamil Kaygısız kamil.kaygisiz1@ogr.sakarya.edu.tr
* @since 03.04.2023
* <p>
*Bir satirda Fonksiyon sayisini return eder
* </p>
*/

package thingsToSearch;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileOperations.abstracts.ICodeProcessor;

public class FunctionCounter implements ICodeProcessor {
    private static final String FUNCTION_REGEX = "(?<=\\n|\\s)\\b((abstract|public|private|protected|static|final|native|synchronized|transient|volatile)\\s+)*\\b([A-Za-z]+[<>\\[\\]\\s]*\\s+)?\\b([A-Za-z_]+)\\s*\\([^\\)]*\\)\\s*(?=\\{|;)";
    private static final Pattern FUNCTION_PATTERN = Pattern.compile(FUNCTION_REGEX, Pattern.DOTALL);
    int totalCount=0;

    @Override
    public void processCode(String codeLine, Map<String, Integer> counts) {
        Matcher functionMatcher = FUNCTION_PATTERN.matcher(codeLine);
        while (functionMatcher.find()) {
            if (counts.containsKey("function")) {
                counts.put("function", counts.get("function") + 1);
            } else {
                counts.put("function", 1);
            }
            totalCount++;
        }
    }

    public int getFunctionCount() {
        return totalCount;
    }

    @Override
    public String getCodeElement() {
        return "function";
    }

}

/*
 is implementable if user wanna now exact equivalents like
 there might be more than void print() etc
   public void processCode(String codeLine, Map<String, Integer> counts) {
        Matcher functionMatcher = FUNCTION_PATTERN.matcher(codeLine);
        int count = 0;
        while (functionMatcher.find()) {
            String functionName = functionMatcher.group().trim();
            counts.merge(functionName, 1, Integer::sum);
            count++;
        }
        counts.merge(getCodeElement(), count, Integer::sum);
    }
 */