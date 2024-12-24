package Graph.BipartiteGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BiPartiteWithBFS {
    public boolean isBipartite(ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[adj.size()];
        Arrays.fill(color,-1);

        for(int i=0;i<adj.size();i++){
            if(color[i]==-1){
                if(!checkBipartite(adj,i,color,1)){
                    return false;
                }
            }
        }


        return true;
    }

    private boolean checkBipartite(ArrayList<ArrayList<Integer>> adj, int currentNode, int[] color, int colorcode) {
        Queue<Integer> queue= new LinkedList<>();
        queue.offer(currentNode);
        color[currentNode] = colorcode;
        while(!queue.isEmpty()){
            int source= queue.remove();
            if(adj.get(source)!=null){
                for(Integer newNode : adj.get(source)){
                    if(color[newNode]==color[source]){
                        return false;
                    }
                    else if(color[newNode]==-1){
                        color[newNode] = 1 - color[source];;
                        queue.offer(newNode);
                    }
                }
            }
        }



        return true;
    }

    public static void main(String[] args) {
        BiPartiteWithBFS b=new BiPartiteWithBFS();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(1, 3))); // 0 -> 1, 3
        adj.add(new ArrayList<>(Arrays.asList(0, 2))); // 1 -> 0, 2
        adj.add(new ArrayList<>(Arrays.asList(1, 3))); // 2 -> 1, 3
        adj.add(new ArrayList<>(Arrays.asList(0, 2))); // 3 -> 0, 2

        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>(Arrays.asList(1, 0))); // 0 -> 1, 0 (self-loop)
        adj2.add(new ArrayList<>(Arrays.asList(0)));    // 1 -> 0

        System.out.println("Is Bipartite with BFS? " + b.isBipartite(adj));
        System.out.println("Is Bipartite with BFS? " + b.isBipartite(adj2));
    }
}
