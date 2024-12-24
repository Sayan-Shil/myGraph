package Graph.CycleDetection.UnDirected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CycleDetectionWithDFS {
    public static boolean cycleDetectionWithDFS(HashMap<Integer, ArrayList<Integer>> hm) {
        boolean[] visited = new boolean[hm.size()];
        int source = 0;
        int parent = -1;

        return cycleDetection(hm, source, parent, visited);
    }

    private static boolean cycleDetection(HashMap<Integer, ArrayList<Integer>> hm, int source, int parent, boolean[] visited) {
        visited[source] = true;

        for (Integer neighbor : hm.get(source)) {
            // Skip the parent node
            if (neighbor == parent) {
                continue;
            }

            // If the neighbor is already visited, a cycle is detected
            if (visited[neighbor]) {
                return true;
            }

            // Recur for unvisited neighbors
            if (cycleDetection(hm, neighbor, source, visited)) {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        hm.put(0, new ArrayList<>(List.of(1, 2)));
        hm.put(1, new ArrayList<>(List.of(3)));
        hm.put(2, new ArrayList<>(List.of(4)));
        hm.put(3, new ArrayList<>());
        hm.put(4, new ArrayList<>());

        System.out.println(cycleDetectionWithDFS(hm));
    }
}
