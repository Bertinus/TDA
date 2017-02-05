import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //-------------------------------------------------------------------------------------
        //Computes the filtrations of d-ball and d-sphere for all d<11
        //-------------------------------------------------------------------------------------
        /*
        for(int i=1; i<11; i++){
            ComputeFiltration.computeBallFiltration(i);
            ComputeFiltration.computeSphereFiltration(i);
        }
        */

        //-------------------------------------------------------------------------------------
        //Computes all the barcodes
        //-------------------------------------------------------------------------------------

        long startTime = System.currentTimeMillis();
        Vector<Simplex> F = ReadFiltration.readFiltration("/home/paul/IdeaProjects/TDA/filtrations/filtration_Projective_Plane.txt");
        BoundaryMatrix matrix = new BoundaryMatrix(F);
        reduce(matrix);
        outputBarcode(matrix, F, "Projective_PlaneBarcode.txt");
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("computation time : "+ time + " millisec");
        System.out.println("dimension : "+ matrix.getDimension());
    }

    public static void reduce(BoundaryMatrix matrix){
        for(int j=1; j<matrix.getDimension(); j++){
            int column = matrix.getSameLowColumn(j);
            while(column != -1){
                matrix.addColumn(column, j);
                column = matrix.getSameLowColumn(j);
            }
        }
    }

    public static void outputBarcode(BoundaryMatrix matrix, Vector<Simplex> F, String fileName){
        int dimension = matrix.getDimension();
        int[] pivots = matrix.getPivots(dimension-1);
        String output = "";

        //Set of all pivots row indices
        TreeSet<Integer> pivotIndices = new TreeSet<>();
        for(int j=0; j<dimension; j++)
            pivotIndices.add(pivots[j]);

        for(int j=0; j<dimension; j++){
            int i = pivots[j];
            if(i == -1 && !pivotIndices.contains(j))
                output += F.get(j).dim+" "+F.get(j).val+" inf\n";
            else{
                if(i != -1)
                    output += F.get(i).dim +" "+F.get(i).val+" "+F.get(j).val+"\n";
            }
        }
        File file = new File(fileName);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(output);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
