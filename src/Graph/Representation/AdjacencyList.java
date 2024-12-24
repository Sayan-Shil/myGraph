package Graph.Representation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class AdjacencyList {
    static HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();

    public static void addEdges( BiConsumer<Integer,Integer> addEdge , int[][] arr){
    Arrays.stream(arr).forEach(array -> addEdge.accept(array[0],array[1]));
    }

    public static void main(String[] args) {
        BiConsumer<Integer,Integer> addEdge = (i1,i2) -> {
            // Search if the key is there
            if(hm.containsKey(i1)){
                hm.get(i1).add(i2);
            }
            else{
                ArrayList<Integer> newlist = new ArrayList<>();
                hm.put(i1,newlist);
                hm.get(i1).add(i2);
            }
        };

        int[][] edges = {
                {0, 1},  // Edge between vertex 0 and vertex 1
                {0, 2},  // Edge between vertex 0 and vertex 2
                {1, 3},  // Edge between vertex 1 and vertex 3
                {2, 4},  // Edge between vertex 2 and vertex 4
                {3, 4},  // Edge between vertex 3 and vertex 4
                {4, 5}   // Edge between vertex 4 and vertex 5
        };

        addEdges(addEdge,edges);
        System.out.println(hm);

    }


}
