package Graph.BipartiteGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BiPartiteWithDFS {
    public boolean isBipartite(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int[] color= new int[adj.size()];
        Arrays.fill(color,-1);

        for(int i=0; i<adj.size();i++){
            if(color[i]==-1){
               if(!colorWithDFS(adj,i,color,1)) {
                   return false;
               }
            }
        }


        return true;
    }

    private boolean colorWithDFS(ArrayList<ArrayList<Integer>> adj, int source, int[] color, int colorcode) {
        color[source]=colorcode;

        if(adj.get(source) != null){
            for(Integer newNode: adj.get(source)){
                if(color[newNode]==color[source]){
                    return false;
                }
                if (color[newNode] == -1) {
                    if (!colorWithDFS(adj, newNode, color, 1 - color[source])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        BiPartiteWithDFS b=new BiPartiteWithDFS();
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        adj1.add(new ArrayList<>(Arrays.asList(1, 3))); // Node 0 connects to 1 and 3
        adj1.add(new ArrayList<>(Arrays.asList(0, 2))); // Node 1 connects to 0 and 2
        adj1.add(new ArrayList<>(Arrays.asList(1, 3))); // Node 2 connects to 1 and 3
        adj1.add(new ArrayList<>(Arrays.asList(0, 2))); // Node 3 connects to 0 and 2

        System.out.println("Is Bipartite? " + b.isBipartite(adj1)); // Expected: true



    }
}
