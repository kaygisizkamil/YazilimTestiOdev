/**
*
* @author Kamil Kaygisizz kamil.kaygisiz1@ogr.sakarya.edu.tr
* @since 03.04.2023
* <p>
* Bu sinif hesaplama islemlerini baslatan us görevi görür,gerekli interfacelere
*  implemeent eden classlar arasinda
* baglantiyi saglar
* </p>
*/

package fileOperations.concretes;

import java.io.IOException;
import java.util.List;

import analyzer.CodeAnalyzer;
import fileOperations.abstracts.ICommentProcessor;

public class CodeProcessor {
    private ICommentProcessor commentProcessor;
    private CodeAnalyzer codeAnalyzer;

    public CodeProcessor(ICommentProcessor commentProcessor, CodeAnalyzer codeAnalyzer) {
        this.commentProcessor = commentProcessor;
        this.codeAnalyzer = codeAnalyzer;
    }

    public void processCode(String filePath) throws  IOException {
        List<String> codeLines = new CodeReader(filePath).readCode(commentProcessor);
        codeAnalyzer.analyze(codeLines);
    }

}