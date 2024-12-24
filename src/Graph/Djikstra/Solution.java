package Graph.Djikstra;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}


// User function Template for Java
public class Solution {
    // Function to find the shortest distance of all the vertices
    // from the source vertex src.
    public ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        // Min-heap to prioritize nodes with the smallest distance
        PriorityQueue<iPair> min_heap = new PriorityQueue<>((i1, i2) -> Integer.compare(i1.second, i2.second));

        // Resultant distance array initialized with infinity
        ArrayList<Integer> result = new ArrayList<>(adj.size());
        for (int i = 0; i < adj.size(); i++) {
            result.add(Integer.MAX_VALUE);
        }

        // Distance to the source is 0
        result.set(src, 0);
        min_heap.offer(new iPair(src, 0));

        while (!min_heap.isEmpty()) {
            iPair p = min_heap.poll(); // Extract the node with the smallest distance
            int source = p.first;
            int distance = p.second;

            // Explore all neighbors of the current node
            for (iPair pair : adj.get(source)) {
                int newDistance = distance + pair.second; // Calculate new possible distance
                if (result.get(pair.first) > newDistance) { // Relaxation check
                    result.set(pair.first, newDistance);   // Update shortest distance
                    min_heap.offer(new iPair(pair.first, newDistance)); // Push updated pair
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        Solution s= new Solution();
        ArrayList<ArrayList<iPair>> list = new ArrayList<>();

        // Data to populate [[[1, 1], [2, 6]], [[2, 3], [0, 1]], [[1, 3], [0, 6]]]
        int[][][] data = {
                {{1, 1}, {2, 6}},
                {{2, 3}, {0, 1}},
                {{1, 3}, {0, 6}}
        };

        // Populate the ArrayList<ArrayList<iPair>> using the data
        for (int[][] outer : data) {
            ArrayList<iPair> innerList = new ArrayList<>();
            for (int[] pair : outer) {
                innerList.add(new iPair(pair[0], pair[1]));
            }
            list.add(innerList);
        }
        System.out.println(s.dijkstra(list,2));

    }
}