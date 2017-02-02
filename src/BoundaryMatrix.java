import java.util.*;

public class BoundaryMatrix {

    SparseMatrix matrix;
    int size;

    BoundaryMatrix(Vector<Simplex> F){
        this.size = F.size();
        this.matrix = new SparseMatrix(size);
        sort(F);

        //On parcourt tous les simplexes
        for(Simplex simplex : F){
            int dim = simplex.dim;
            //Pour chaque simplexe, on retrouve ses frontieres de dimension dim-1 a partir des vertices
            for(Simplex s : F){
                if(s.dim==dim-1 && simplex.vert.containsAll(s.vert)){
                    matrix.setToOne(F.indexOf(s), F.indexOf(simplex));
                }
            }
        }
    }

    public void sort(Vector<Simplex> F){
        Comparator<Simplex> comparator = new SimplexComparator();
        Collections.sort(F, comparator);
    }


    public String toString(){
        String s = "";
        for(int i=0; i<size; i++){
            s+="[ ";
            for(int j=0; j<size; j++){
                s+=this.matrix.get(i, j) + " ";
            }
            s+="]\n";
        }
        return s;
    }

    public void reduce(){
        matrix.addColumn(6,7);
    }

}
