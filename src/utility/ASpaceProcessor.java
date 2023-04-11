/**
*
* @author Kamil Kaygısız kamil.kaygisiz1@ogr.sakarya.edu.tr
* @since 03.04.2023
* <p>
* String icindeki bosluklari siler
* </p>
*/
package utility;

public  abstract class ASpaceProcessor {
    public String deleteSpaces(String line){
        return line.replaceAll("\\s+", "");
    }
}