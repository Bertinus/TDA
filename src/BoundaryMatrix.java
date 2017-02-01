import java.util.*;

public class BoundaryMatrix {

    int[][] matrix;
    int size;

    BoundaryMatrix(Vector<Simplex> F){
        this.size = F.size();
        this.matrix = new int[size][size];
        sort(F);

        //On parcourt tous les simplexes
        for(Simplex simplex : F){
            int dim = simplex.dim;
            //Pour chaque simplexe, on retrouve ses frontieres de dimension dim-1 a partir des vertices
            for(Simplex s : F){
                if(s.dim==dim-1 && simplex.vert.containsAll(s.vert)){
                    matrix[F.indexOf(s)][F.indexOf(simplex)] = 1;
                }
            }
        }
    }

    public void sort(Vector<Simplex> F){
        Comparator<Simplex> comparator = new SimplexComparator();
        Collections.sort(F, comparator);
    }

    public List<Integer> getBoundaries(Simplex s, Vector<Simplex> F){

        for(int v : s.vert){

        }
        return null;
    }


    public String toString(){
        String s = "";
        for(int i=0; i<size; i++){
            s+="[ ";
            for(int j=0; j<size; j++){
                s+=this.matrix[i][j]+" ";
            }
            s+="]\n";
        }
        return s;
    }

}
