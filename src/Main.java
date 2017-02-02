import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Vector<Simplex> F = ReadFiltration.readFiltration("/home/paul/IdeaProjects/TDA/Test.txt");
        BoundaryMatrix matrix = new BoundaryMatrix(F);
        System.out.println(matrix);
        matrix.reduce();
        System.out.println(matrix);
        System.out.println(F);

    }
}
