package Graph.CycleDetection.UnDirected;

import java.util.*;

public class CycleDetectionWithBFS
 {
    public static boolean cycleDetectionWithBFS
(HashMap<Integer, ArrayList<Integer>> hm) {
        boolean[] visited = new boolean[hm.size()];

        Queue<int[]> q = new LinkedList<>();
        for( Integer node : hm.keySet()){
            if(!visited[node]){
                q.add(new int[]{node,-1}); //First Node
                visited[node] = true;
                while(!q.isEmpty()){
                    int[] caller = q.poll();
                    int currentNode= caller[0];
                    int parent = caller[1];

                    for( Integer i : hm.get(currentNode)){
                        if(visited[i] && i!=parent){
                            return true;
                        }
                        else{
                            visited[i]=true;
                            q.add(new int[]{i,currentNode});
                        }
                    }
                }
            }
        }
        return false;
    }



    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        hm.put(0, new ArrayList<>(List.of(1, 2)));
        hm.put(1, new ArrayList<>(Arrays.asList(0, 3)));
        hm.put(2, new ArrayList<>(List.of(0)));
        hm.put(3, new ArrayList<>(Arrays.asList(1, 4)));
        hm.put(4, new ArrayList<>(List.of(3)));
        System.out.println(cycleDetectionWithBFS
(hm));
    }
}
