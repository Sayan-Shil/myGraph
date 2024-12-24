package Graph.EularPath;

import java.util.List;

public class Solution {
    public int isEulerCircuit(int V, List<Integer>[] adj)
    {
       if(!ifConnected(V,adj)){
           return 0;
       }

       int numberOfOddDegree = 0;

       for(int i=0;i<V;i++){
           if(adj[i].size()%2==1){
               numberOfOddDegree++;
           }
       }

       return (numberOfOddDegree==2) ? 1 : numberOfOddDegree>2 ? 0 : 2;

    }

    private boolean ifConnected(int V, List<Integer>[] adj) {
        // Take any non-zero degree element
        int firstNonZero=-1;
        for(int i=0;i<adj.length;i++){
            if(!adj[i].isEmpty()){
                firstNonZero=i;
            }
        }
        boolean[] visited = new boolean[V];
        BFS(adj,firstNonZero,visited);

        for(int i=0;i<V;i++){
            if(!adj[i].isEmpty() && !visited[i]){
                return false;
            }
        }
        return true;
    }

    private void BFS(List<Integer>[] adj, int firstNonZero, boolean[] visited) {
        visited[firstNonZero]=true;
        if(adj[firstNonZero]!=null){
            for(Integer node : adj[firstNonZero]){
                if(!visited[node]){
                    BFS(adj,node,visited);
                }
            }
        }
    }
}
