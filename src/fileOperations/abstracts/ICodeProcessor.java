package fileOperations.abstracts;

import java.util.Map;

public interface ICodeProcessor {
    void processCode(String codeLine, Map<String, Integer> counts);
    String getCodeElement();
}