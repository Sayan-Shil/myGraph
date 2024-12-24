package Graph.CycleDetection.Directed;

import java.util.*;

public class KahnAlgorithm
 {
    public static boolean checkCycle(ArrayList<ArrayList<Integer>> adj) {
        // Your code here
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        HashMap<Integer,ArrayList<Integer>> hm = new HashMap<>();
        final int[] p = {0};
        adj.forEach(al -> {
            hm.put(p[0], al);
            p[0]++;
        });

        int[] inDegree = new int[hm.size()];
        Arrays.fill(inDegree,0);
        for (int i = 0; i < hm.size(); i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }

        for(int i=0; i<inDegree.length;i++){
            if(inDegree[i]==0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int node = q.remove();
                result.add(node);
                for(Integer i1: hm.get(node)){
                    inDegree[i1]--;
                    if(inDegree[i1]==0)
                        q.offer(i1);
                }

        }

       if(result.size()==hm.size()){
           return false;
       }
       else{
           return true;
       }

    }



    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(List.of(1)));
        adj.add(new ArrayList<>(List.of(2)));
        adj.add(new ArrayList<>(List.of(0)));

        System.out.println(checkCycle(adj));


    }
}
