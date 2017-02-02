import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;

public class ReadFiltration {

    static Vector<Simplex> readFiltration (String filename) throws FileNotFoundException {
        Vector<Simplex> F = new Vector<Simplex>();
        Scanner sc = new Scanner(new File(filename));
        sc.useLocale(Locale.US);
        while (sc.hasNext())
            F.add(new Simplex(sc));
        sc.close();
        return F;
    }
}
