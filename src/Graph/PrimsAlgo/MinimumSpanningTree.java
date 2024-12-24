package Graph.PrimsAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
    static class Pair {
        int weight;
        int node;

        Pair(int node, int weight){
            this.weight = weight;
            this.node = node;
        }
    }
    static int spanningTree(int V, int E, List<List<int[]>> adj) {

        boolean[] visited= new boolean[V];
        Arrays.fill(visited,false);

       PriorityQueue<Pair> minHeap = new PriorityQueue<>((a,b)-> Integer.compare(a.weight,b.weight));
       minHeap.add(new Pair(0,0));
       int sum=0;

       while(!minHeap.isEmpty()){
           Pair pair = minHeap.poll();
           int destination = pair.node;
           int weight = pair.weight;

           if(visited[destination]){
               continue;
           }
           visited[destination]=true; // Optimal Solution got
           sum+=weight;

           for(int[] temp : adj.get(destination)){
               int newDestination = temp[0];
               int newDistance = temp[1];
               if(!visited[newDestination]){
                   minHeap.offer(new Pair(newDestination,newDistance));
               }
           }



       }

       return sum;

    }

    public static void main(String[] args) {
        int numVertices = 7;

        // Initialize the adjacency list
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Directly adding edges to the adjacency list
        graph.get(0).add(new int[]{1, 20});
        graph.get(1).add(new int[]{0, 20});

        graph.get(1).add(new int[]{2, 15});
        graph.get(2).add(new int[]{1, 15});

        graph.get(2).add(new int[]{3, 3});
        graph.get(3).add(new int[]{2, 3});

        graph.get(3).add(new int[]{4, 17});
        graph.get(4).add(new int[]{3, 17});

        graph.get(4).add(new int[]{5, 29});
        graph.get(5).add(new int[]{4, 29});

        graph.get(5).add(new int[]{0, 23});
        graph.get(0).add(new int[]{5, 23});

        graph.get(0).add(new int[]{6, 1});
        graph.get(6).add(new int[]{0, 1});

        graph.get(1).add(new int[]{6, 4});
        graph.get(6).add(new int[]{1, 4});

        graph.get(2).add(new int[]{6, 9});
        graph.get(6).add(new int[]{2, 9});

        graph.get(3).add(new int[]{6, 16});
        graph.get(6).add(new int[]{3, 16});

        graph.get(4).add(new int[]{6, 25});
        graph.get(6).add(new int[]{4, 25});

        graph.get(5).add(new int[]{6, 40});
        graph.get(6).add(new int[]{5, 40});

        System.out.println(spanningTree(7, 12, graph)); // Output the total weight of the MST
    }
}
