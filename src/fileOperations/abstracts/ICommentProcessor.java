/**
*
* @author Kamil Kaygısız kamil.kaygisiz1@ogr.sakarya.edu.tr
* @since 03.04.2023
* <p>
* Bu interface icinde classa gelen satirin comment icerikli stringin discard edilmesini garanti eder
* </p>
*/

package fileOperations.abstracts;

public interface ICommentProcessor {
    String processLineDeleteComments(String line);
}

