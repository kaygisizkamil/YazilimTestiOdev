package utility;

public  abstract class ASpaceProcessor {
    public String deleteSpaces(String line){
        return line.replaceAll("\\s+", "");
    }
}