import java.io.FileNotFoundException;
import java.util.TreeSet;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Vector<Simplex> F = ReadFiltration.readFiltration("/home/paul/IdeaProjects/TDA/Test.txt");
        SparseMatrix matrix = new SparseMatrix(F);
        System.out.println(F);
        System.out.println(matrix);
        reduce(matrix);
        System.out.println(matrix);
        outputeBarcode(matrix, F);

    }

    public static void reduce(SparseMatrix matrix){
        for(int j=1; j<matrix.getDimension(); j++){
            int column = matrix.getSameLowColumn(j);
            while(column != -1){
                matrix.addColumn(column, j);
                column = matrix.getSameLowColumn(j);
            }
        }
    }

    public static void outputeBarcode(SparseMatrix matrix, Vector<Simplex> F){
        int[] pivots = matrix.getPivots();
        int dimension = matrix.getDimension();
        //Set of all pivots row indices
        TreeSet<Integer> pivotIndices = new TreeSet<>();
        for(int j=0; j<dimension; j++)
            pivotIndices.add(pivots[j]);

        for(int j=0; j<dimension; j++){
            int i = pivots[j];
            if(i == -1 && !pivotIndices.contains(j))
                System.out.println(F.get(j).dim+" "+F.get(j).val+" inf");
            else{
                if(i != -1)
                    System.out.println(F.get(i).dim +" "+F.get(i).val+" "+F.get(j).val);
            }
        }
    }

}
