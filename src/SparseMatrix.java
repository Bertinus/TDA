import java.util.HashMap;
import java.util.TreeSet;

public class SparseMatrix {

    //On map chaque colonne et on indique les indices contenant des uns
    private HashMap<Integer, TreeSet<Integer>> matrix;
    private int dimension;

    public SparseMatrix(int dimension){
        this.dimension = dimension;
        this.matrix = new HashMap<>();
    }

    public void setToOne(int i, int j) throws IndexOutOfBoundsException{
        if(!(i>=0) || !(j>=0) || !(i<this.dimension) || !(j<this.dimension))
            System.exit(0);
        else{
            if( !matrix.containsKey(j)){
                matrix.put(j, new TreeSet<>());
            }
            matrix.get(j).add(i);
        }
    }

    public int get(int i, int j){
        TreeSet<Integer> set = matrix.get(j);
        if(set == null || !set.contains(i))
            return 0;
        return 1;
    }

    public int getPivot(int j){
        return matrix.get(j).last();
    }

    public void addColumn(int i, int j){
        //adds column i to column j
        TreeSet<Integer> seti = matrix.get(i);
        TreeSet<Integer> setj = matrix.get(j);

        for(int k : seti){
            if(setj.contains(k))
                setj.remove(k);
            else{
                setj.add(k);
            }
        }
    }

}
