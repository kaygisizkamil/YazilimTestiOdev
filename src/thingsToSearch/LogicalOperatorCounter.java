package thingsToSearch;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.JOptionPane.showMessageDialog;

import fileOperations.abstracts.ICodeProcessor;

public class LogicalOperatorCounter implements ICodeProcessor {
	//it founds &&,||,! logical operators
   //if there is no space before or after will return  operator using regexes become really hard to do
	//please be sure your code auto corrected inside the compiler before running the test
	//operatorlerden once bosluk olmazsa eksik  sayi dondurur lutfen compileriniz ile formata dikkat ettikten
	//sonra deneyin
    private static final String LOGICAL_OPERATORS_REGEX = "(?<!(\\w|[\"']))(!|&&|\\|\\|)(?!(\\w|[\"'])|(?<=!)=(?!(\\w|[\"'])))";
   private int count=0;

    @Override
    public void processCode(String codeLine, Map<String, Integer> counts) {
        codeLine = codeLine.replaceAll("(\\/\\/.*$|\\/\\*.*?\\*\\/)", "");
        Matcher matcher = Pattern.compile(LOGICAL_OPERATORS_REGEX).matcher(codeLine);

        while (matcher.find()) {
            count++;
            String operator = matcher.group();
            counts.put(operator, counts.getOrDefault(operator, 0) + 1);
        }
    }
    public int logicalOperatorCount(){
        return count;
    }

    @Override
    public String getCodeElement() {
        return "Logical Operators";
    }

}
