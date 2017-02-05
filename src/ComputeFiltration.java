import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComputeFiltration {

    public static void computeSphereFiltration(int d){
        File file = new File("sphereFiltrations/"+d+"sphere.txt");
        String filtration = filtration(d);
        try {
            //Computes the filtration of the d-ball and removes the last simplex
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            int truncateIndex = filtration.length();
            truncateIndex = filtration.lastIndexOf('\n', truncateIndex - 1);
            truncateIndex = filtration.lastIndexOf('\n', truncateIndex - 1);
            out.write(filtration.substring(0, truncateIndex));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void computeBallFiltration(int d){
        File file = new File("ballFiltrations/"+d+"ball.txt");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(filtration(d));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String filtration(int d){
        //Computes and returns the filtration of the d-ball
        String filtration = "";
        //superSet is the set of all integers i such that  1<=i<=d
        List<Integer> superSet = new ArrayList<>();
        List<Set<Integer>> list;
        for(int i=1; i<d+2; i++)
            superSet.add(i);

        for(int time=1; time<d+2; time ++){
            //list contains all subsets of "superSet" of size "time"
            list = getSubsets(superSet, time);
            while (!list.isEmpty()){
                Set<Integer> set = list.remove(0);
                filtration += time + ".0 " + (time-1) + " ";
                for(int i : set)
                    filtration += i + " ";
                filtration += "\n";
            }
        }
        return filtration;
    }

    //-------------------------------------------------------------------------------------
    //Methods to get all the possible subsets of k elements in the given superset
    //-------------------------------------------------------------------------------------

    private static void getSubsets(List<Integer> superSet, int k, int idx, Set<Integer> current, List<Set<Integer>> solution) {
        //successful stop clause
        if (current.size() == k) {
            solution.add(new HashSet<>(current));
            return;
        }
        //unseccessful stop clause
        if (idx == superSet.size()) return;
        Integer x = superSet.get(idx);
        current.add(x);
        //"guess" x is in the subset
        getSubsets(superSet, k, idx+1, current, solution);
        current.remove(x);
        //"guess" x is not in the subset
        getSubsets(superSet, k, idx+1, current, solution);
    }

    public static List<Set<Integer>> getSubsets(List<Integer> superSet, int k) {
        List<Set<Integer>> res = new ArrayList<>();
        getSubsets(superSet, k, 0, new HashSet<Integer>(), res);
        return res;
    }



}
