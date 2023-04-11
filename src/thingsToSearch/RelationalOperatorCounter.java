/**
*
* @author Kamil Kaygısız kamil.kaygisiz1@ogr.sakarya.edu.tr
* @since 03.04.2023
* <p>
*Bir satirda relational operator sayisini return eder
*<
<=
>
>=
==
!
* </p>
*/
package thingsToSearch;


import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileOperations.abstracts.ICodeProcessor;

public class RelationalOperatorCounter implements ICodeProcessor {
	/*<
<=
>
>=
==
!=*/
    private static final String RELATIONAL_OPERATORS_REGEX = "(?<!\\S)(==|!=|[<>]=?)(?!\\S)";
    private int count=0;
    @Override
    public void processCode(String codeLine, Map<String, Integer> counts) {
        Matcher matcher = Pattern.compile(RELATIONAL_OPERATORS_REGEX).matcher(codeLine);

        while (matcher.find()) {
            count++;
            String operator = matcher.group();
            counts.put(operator, counts.getOrDefault(operator, 0) + 1);
        //    System.out.println("Added operator: " + operator + ", count: " + counts.get(operator));

        }
    }
  public int getRelationalCount(){
        return count;
  }
    @Override
    public String getCodeElement() {
        return "Relational Operators";
    }

}
