package Graph.BellmanFord;

import java.util.Arrays;

public class Solution {
    static int[] bellmanFord(int V, int[][] edges, int src) {
       int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e8);
        dist[0]=0;
        for(int i=0;i<V-1;i++){
            
            for(int[] arr : edges){
             int u = arr[0];
             int v = arr[1];
             int weight = arr[2];
             if(dist[u]+ weight < dist[v] && dist[u]!= 1e8){
                 dist[v] = dist[u]+ weight;
             }
            }
        }

        for(int[] arr : edges){
            int u = arr[0];
            int v = arr[1];
            int weight = arr[2];
            if(dist[u]+ weight < dist[v] && dist[u]!= 1e8){
                return new int[]{-1};
            }
        }

return dist;

    }
}
