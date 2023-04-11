/**
*
* @author Kamil Kaygısız kamil.kaygisiz1@ogr.sakarya.edu.tr
* @since 03.04.2023
* <p>
* Bu interface ortak methodları olan classlarin kullanması icin olusturulmustur
* * </p>
*/

package fileOperations.abstracts;

import java.util.Map;

public interface ICodeProcessor {
    void processCode(String codeLine, Map<String, Integer> counts);
    String getCodeElement();
}