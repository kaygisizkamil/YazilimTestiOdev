package thingsToSearch;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileOperations.abstracts.ICodeProcessor;
import utility.ASpaceProcessor;

public class UnaryOperatorCounter extends  ASpaceProcessor implements ICodeProcessor {/*
Pre-increment: ++var
Pre-decrement: --var
Post-increment: var++
Post-decrement: var--
Positive sign: +var (excluding the ++ operator)
Negative sign: -var (excluding the -- operator)
this class has a bit deeper implementation ,this can be applicable to other operators but will take much more 
time since the aim of this homework is testing not developing i wanted show the implementation go much deeper
maybe abstract tree's also can be used if had much more time,i always thinked that class be max 100 line of
code because of solid principles but i looked how parser implemented and realized it can be much more than that.
*/
	    private int unaryOperatorCount = 0;
	    private final static String PRE_INCREMENT_PATTERN = "\\+\\+([a-zA-Z_][a-zA-Z0-9_]*)";
	    private final static String PRE_DECREMENT_PATTERN = "--([a-zA-Z_][a-zA-Z0-9_]*)";
	    private final static String POST_INCREMENT_PATTERN = "([a-zA-Z_][a-zA-Z0-9_]*)\\+\\+";
	    private final static String POST_DECREMENT_PATTERN = "([a-zA-Z_][a-zA-Z0-9_]*)--";
	    private final static String POSITIVE_PATTERN = "(?<!\\w)(\\+)(?!\\+)";
	    private final static String NEGATIVE_PATTERN = "(?<!\\w)(\\-)(?!\\-)";
	    private final static String STRING_LITERAL_PATTERN = "\"(\\\\.|[^\"])*\"";
	    private final static String CHARACTER_LITERAL_PATTERN = "'(\\\\.|[^'])'";

	    public void processCode(String codeLine, Map<String, Integer> counts) {
	        if (isStringLiteral(codeLine)) {
	            return;
	        }

	        if (isCharacterLiteral(codeLine)) {
	            return;
	        }

	        countUnaryOperators(codeLine, counts);
	    }

	    private boolean isComment(String codeLine) {
	        return codeLine.trim().startsWith("//");
	    }

	    private boolean isStringLiteral(String codeLine) {
	        Pattern stringLiteralPattern = Pattern.compile(STRING_LITERAL_PATTERN);
	        Matcher stringLiteralMatcher = stringLiteralPattern.matcher(codeLine);
	        return stringLiteralMatcher.find();
	    }

	    private boolean isCharacterLiteral(String codeLine) {
	        Pattern characterLiteralPattern = Pattern.compile(CHARACTER_LITERAL_PATTERN);
	        Matcher characterLiteralMatcher = characterLiteralPattern.matcher(codeLine);
	        return characterLiteralMatcher.find();
	    }

	    private void countUnaryOperators(String codeLine, Map<String, Integer> counts) {
	        codeLine=deleteSpaces(codeLine);//delete the spaces for finding true count
	        countPreIncrement(codeLine, counts);
	        countPreDecrement(codeLine, counts);
	        countPostIncrement(codeLine, counts);
	        countPostDecrement(codeLine, counts);
	        countPositiveSign(codeLine, counts);
	        countNegativeSign(codeLine, counts);
	    }

	    private void countPreIncrement(String codeLine, Map<String, Integer> counts) {
	        Pattern pattern = Pattern.compile(PRE_INCREMENT_PATTERN);
	        Matcher matcher = pattern.matcher(codeLine);
	        while (matcher.find()) {
	            unaryOperatorCount++;
	            counts.merge(matcher.group(), 1, Integer::sum);
	        }
	    }

	    private void countPreDecrement(String codeLine, Map<String, Integer> counts) {
	        Pattern pattern = Pattern.compile(PRE_DECREMENT_PATTERN);
	        Matcher matcher = pattern.matcher(codeLine);
	        while (matcher.find()) {
	            unaryOperatorCount++;
	            counts.merge(matcher.group(), 1, Integer::sum);
	        }
	    }

	    private void countPostIncrement(String codeLine, Map<String, Integer> counts) {
	        Pattern pattern = Pattern.compile(POST_INCREMENT_PATTERN);
	        Matcher matcher = pattern.matcher(codeLine);
	        while (matcher.find()) {
	            unaryOperatorCount++;
	            counts.merge(matcher.group(), 1, Integer::sum);
	        }
	    }

	    private void countPostDecrement(String codeLine, Map<String, Integer> counts) {
	        Pattern pattern = Pattern.compile(POST_DECREMENT_PATTERN);
	        Matcher matcher = pattern.matcher(codeLine);
	        while (matcher.find()) {
	            unaryOperatorCount++;
	            counts.merge(matcher.group(), 1, Integer::sum);
	        }
	    }
	    private void countPositiveSign(String codeLine, Map<String, Integer> counts){
	        Pattern positivePattern = Pattern.compile(POSITIVE_PATTERN);
	        Matcher positiveMatcher = positivePattern.matcher(codeLine);
	        while (positiveMatcher.find()) {
	            // Skip the match if it's preceded by another + or -
	            if (positiveMatcher.start() > 0) {
	                char prevChar = codeLine.charAt(positiveMatcher.start() - 1);
	                if (prevChar == '+' || prevChar == '-') {
	                    continue;
	                }
	            }
	            unaryOperatorCount++;
	            counts.merge(positiveMatcher.group(), 1, Integer::sum);
	        }
	    }


	    private void countNegativeSign(String codeLine, Map<String, Integer> counts) {
	        Pattern negativePattern = Pattern.compile(NEGATIVE_PATTERN);
	        Matcher negativeMatcher = negativePattern.matcher(codeLine);
	        while (negativeMatcher.find()) {
	            // Skip the match if it's preceded by another + or -
	            if (negativeMatcher.start() > 0) {
	                char prevChar = codeLine.charAt(negativeMatcher.start() - 1);
	                if (prevChar == '+' || prevChar == '-') {
	                    continue;
	                }
	            }
	            unaryOperatorCount++;
	            counts.merge(negativeMatcher.group(), 1, Integer::sum);
	        }
	    }

	    @Override
	    public String getCodeElement() {
	        return "Unary Operators";
	    }

	    public int getUnaryOperatorCount() {
	        return unaryOperatorCount;
	    }

}
