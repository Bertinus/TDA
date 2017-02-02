import java.io.FileNotFoundException;
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
                System.out.println(matrix);
                column = matrix.getSameLowColumn(j);
            }
        }
    }

    public static void outputeBarcode(SparseMatrix matrix, Vector<Simplex> F){
        for(int j=0; j<matrix.getDimension(); j++){
            int i = matrix.getLowIndex(j);
            if(i != -1){
                System.out.println(F.get(i).dim +" "+F.get(i).val+" "+F.get(j).val);
            }
            else{
                boolean lineOfZeros = true;
                for(int k=0; k<matrix.getDimension(); k++){
                    lineOfZeros = lineOfZeros && (matrix.get(i, k)==0);
                }
                if(lineOfZeros)
                    System.out.println(F.get(i).dim+" "+F.get(i).val+" inf");
            }
        }
    }

}
