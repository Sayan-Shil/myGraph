package Graph.Kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    static int spanningTree(int V, List<List<int[]>> adj) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)-> Integer.compare(a[2],b[2]));
        int[] parent = new int[V];
        int[] rank= new int[V];
        for(int i=0;i<V;i++){
            parent[i]=i;
            rank[i]=0;
        }

        for(int i=0;i<V;i++){
            for(int[] arr : adj.get(i)){
                minHeap.offer(new int[]{i,arr[0],arr[1]});
            }
        }

        int sum=0;
        List<int[]> mstEdges = new ArrayList<>();
        while(!minHeap.isEmpty()){
            int[] list = minHeap.poll();
            int source= list[0];
            int node = list[1];
            int distance = list[2];

            int x = findParent(source,parent);
            int y= findParent(node,parent);

            if(x!=y){
                Union(x,y,parent,rank);
                mstEdges.add(new int[]{source, node, distance});
                System.out.println(distance+ " added");
                sum += distance;
            }

        }

        System.out.println("\nEdges in the Minimum Spanning Tree:");
        for (int[] edge : mstEdges) {
            System.out.println("(" + edge[0] + ", " + edge[1] + ") with weight " + edge[2]);
        }

        return sum;
    }
    private static void Union(int x, int y, int[] parent, int[] rank) {
        int rootX = findParent(x, parent);
        int rootY = findParent(y, parent);

        if (rootX != rootY) {
            // Union by rank
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    private static int findParent(int node, int[] parent) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node], parent); // Path compression
        }
        return parent[node];
    }

    public static void main(String[] args) {
        // Number of vertices
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

        System.out.println(spanningTree(7,graph));
    }

}
