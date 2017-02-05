import java.util.*;

public class BoundaryMatrix {

    //HashMap :
    // key : column
    // value :row indices filled with ones
    private HashMap<Integer, TreeSet<Integer>> matrix;
    private int dimension;

    //-------------------------------------------------------------
            //Constructor
    //-------------------------------------------------------------


    //Creates the boundary matrix of F
    public BoundaryMatrix(Vector<Simplex> F){
        this.matrix = new HashMap<>();
        this.dimension = F.size();
        sort(F);

        int simplexIdx = 0;
        //We loop over all simplices
        for(Simplex simplex : F){
            int dim = simplex.dim;
            int sIdx = 0;
            //For each simplex, we retrieve its dim-1 dimensional boundaries
            if(dim > 0) {
                for (Simplex s : F) {
                    if (sIdx >= simplexIdx)
                        break;
                    if (s.dim == dim - 1 && simplex.vert.containsAll(s.vert)) {
                        this.setToOne(sIdx, simplexIdx);
                    }
                    sIdx++;
                }
            }
            simplexIdx ++;
        }
    }

    //--------------------------------------------------------------
        //Setters and Getters
    //--------------------------------------------------------------

    public void setToOne(int i, int j) throws IndexOutOfBoundsException{
        if( !matrix.containsKey(j)){
            matrix.put(j, new TreeSet<>());
        }
        matrix.get(j).add(i);
    }

    public int get(int i, int j){
        TreeSet<Integer> set = matrix.get(j);
        if(set == null || !set.contains(i))
            return 0;
        return 1;
    }

    public int getDimension(){
        return dimension;
    }

    public int[] getPivots(int indexMax){
        int[] pivots = new int[indexMax+1];
        for(int j=0; j<indexMax+1; j++){
            if(matrix.get(j) == null || matrix.get(j).isEmpty())
                pivots[j] = -1;
            else
                pivots[j] = matrix.get(j).last();
        }
        return pivots;
    }

    //--------------------------------------------------------------
        //Operations on matrices
    //--------------------------------------------------------------

    public int getSameLowColumn(int j){
        int[] pivots = getPivots(j);
        if(pivots[j]==-1)
            return -1;
        int pivot = pivots[j];
        //Check if the columns have same low and return it, else return -1
        for(int k=0; k<j; k++){
            if(pivots[k]==pivot)
                return k;
        }
        return -1;
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

    //---------------------------------------------------------------

    public void sort(Vector<Simplex> F){
        Comparator<Simplex> comparator = new SimplexComparator();
        Collections.sort(F, comparator);
    }

    @Override
    public String toString(){
        String s = "";
        for(int i=0; i<dimension; i++){
            s+="[ ";
            for(int j=0; j<dimension; j++){
                s+=this.get(i, j) + " ";
            }
            s+="]\n";
        }
        return s;
    }

}
