import java.util.*;

public class Simplex {

    float val;
    int dim;
    TreeSet<Integer> vert;

    Simplex(Scanner sc){
        val = sc.nextFloat();
        dim = sc.nextInt();
        vert = new TreeSet<Integer>();
        for (int i=0; i<=dim; i++)
            vert.add(sc.nextInt());
    }

    public String toString(){
        return "{val="+val+"; dim="+dim+"; "+vert+"}\n";
    }

}

class SimplexComparator implements Comparator<Simplex>{
    @Override
    public int compare(Simplex simplex, Simplex t1) {
        return (int) (simplex.val - t1.val);
    }
}
