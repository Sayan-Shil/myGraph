package Graph.DisJoint_Union_Set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CycleDetectionWithDSU {
    public boolean detectCycle(int V, ArrayList<ArrayList<Integer>> adj) {
       int[] parent = new int[V];
       int[]  rank = new int[V];
       for(int i=0; i<V;i++){
           parent[i]=i;
           rank[i]=0;
       }

       for(int i=0; i<V;i++){
           if(adj.get(i)!=null){
               for(Integer num : adj.get(i)){
                   if(num>i){
                       num= findParent(num,parent);
                       i= findParent(i,parent);

                       if(parent[i]==parent[num]){
                           return true;
                       }
                       else{
                           
                           unionTwoSet(num,i,parent,rank);

                       }
                   }
               }
           }

       }

       return false;
    }

    private void unionTwoSet(int num1, int num2, int[] parent, int[] rank) {
        if(rank[num1]==rank[num2]){
            parent[num1]=num2;
            rank[num2]++;
        }
        else if(rank[num1]>rank[num2]){
            parent[num2]=num1;
        }
        else{
            parent[num1]=num2;
        }
    }

    private int findParent(Integer num, int[] parent) {
        if(num==parent[num]){
            return num;
        }
        else{
            return parent[num]= findParent(parent[num],parent);
        }
    }


    public static void main(String[] args) {
        CycleDetectionWithDSU list = new CycleDetectionWithDSU();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

// Creating adjacency list for the undirected graph
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }

// Adding edges to the graph
        adj.get(0).add(1); // Edge 0-1
        adj.get(1).add(0);

        adj.get(1).add(2); // Edge 1-2
        adj.get(2).add(1);

        adj.get(2).add(3); // Edge 2-3
        adj.get(3).add(2);

        adj.get(3).add(1); // Edge 3-1 (This creates a cycle: 1 -> 2 -> 3 -> 1)
        adj.get(1).add(3);

        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>(Arrays.asList(1, 3))); // 0 -> 1, 3
        adj2.add(new ArrayList<>(Arrays.asList(0, 2))); // 1 -> 0, 2
        adj2.add(new ArrayList<>(Arrays.asList(1, 3))); // 2 -> 1, 3
        adj2.add(new ArrayList<>(Arrays.asList(0, 2))); // 3 -> 0, 2

        ArrayList<ArrayList<Integer>> adj3 = new ArrayList<>();
        adj3.add(new ArrayList<>(Arrays.asList(1, 0))); // 0 -> 1, 0 (self-loop)
        adj3.add(new ArrayList<>(Arrays.asList(0)));

        System.out.println(list.detectCycle(4,adj));
        System.out.println(list.detectCycle(4,adj2));
        System.out.println(list.detectCycle(2,adj3));
    }
}
