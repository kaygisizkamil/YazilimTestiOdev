/**
*
* @author Kamil Kaygısız kamil.kaygisiz1@ogr.sakarya.edu.tr
* @since 03.04.2023
* <p>
* Bir satir okur ve okunan satiri commentProcessore gonderir loosecoupling sayesinde implement eden class
* gerekli gorevleri yerine getircektir
* </p>
*/


package fileOperations.concretes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileOperations.abstracts.ICommentProcessor;

public class CodeReader {
    private String filePath;

    public CodeReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> readCode(ICommentProcessor commentProcessor) throws IOException {
    	BufferedReader reader=new BufferedReader(new FileReader(filePath));
    	List<String>codeLines=new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String processedLine = commentProcessor.processLineDeleteComments(line);
            codeLines.add(processedLine);
        }

        reader.close();
        return codeLines;
    }

}