package fileOperations.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileOperations.abstracts.ICommentProcessor;

public class CommentProcessor implements ICommentProcessor {
    private final Pattern startComment = Pattern.compile("\\/\\*+");
    private final Pattern endComment = Pattern.compile("\\*+\\/");
    private final Pattern singleLineComment = Pattern.compile("//.*");
    private final Pattern multiLineComment = Pattern.compile("(?s)/\\*(.)*?\\*/");

    private boolean isInMultiLineComment = false;

    @Override
    public String processLineDeleteComments(String line) {
        StringBuilder processedLine = new StringBuilder();
        int lineStartIndex = 0;

        while (true) {
            if (isInMultiLineComment) {
                Matcher endMatcher = endComment.matcher(line.substring(lineStartIndex));
                if (endMatcher.find()) {
                    isInMultiLineComment = false;
                    lineStartIndex += endMatcher.end();
                } else {
                    break;
                }
            } else {
                Matcher startMatcher = startComment.matcher(line.substring(lineStartIndex));
                Matcher singleMatcher = singleLineComment.matcher(line.substring(lineStartIndex));
                Matcher multiMatcher = multiLineComment.matcher(line.substring(lineStartIndex));
                if (startMatcher.find()) {
                    isInMultiLineComment = true;
                    lineStartIndex += startMatcher.end();
                } else if (multiMatcher.find()) {
                    processedLine.append(line.substring(lineStartIndex, multiMatcher.start()));
                    lineStartIndex += multiMatcher.end();
                } else if (singleMatcher.find()) {
                    processedLine.append(line.substring(lineStartIndex, singleMatcher.start()));
                    lineStartIndex += singleMatcher.end();
                } else {
                    processedLine.append(line.substring(lineStartIndex));
                    break;
                }
            }
        }
        String returnValue=processedLine.toString();
        return returnValue;
    }
}
