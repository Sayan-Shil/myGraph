package Graph.LeetCode.min_cost_to_connect_all_points;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MinCostToConnectPoints {

    static class Pair {
        int node;
        int weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        // Adjacency list representation of the graph
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the adjacency list
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                adj.get(i).add(new Pair(j, distance));
                adj.get(j).add(new Pair(i, distance));
            }
        }

        return PrimsAlgo(adj, n);
    }

    private int PrimsAlgo(ArrayList<ArrayList<Pair>> adj, int length) {
        boolean[] visited = new boolean[length];
        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));

        int totalCost = 0;
        queue.offer(new Pair(0, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            int node = current.node;
            int weight = current.weight;

            if (visited[node]) continue;

            visited[node] = true;
            totalCost += weight;

            for (Pair neighbor : adj.get(node)) {
                if (!visited[neighbor.node]) {
                    queue.offer(neighbor);
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        MinCostToConnectPoints obj = new MinCostToConnectPoints();
        int[][] points = {
                {3, 12},
                {-2, 5},
                {-4, 1}
        };

        System.out.println("Minimum Cost to Connect Points: " + obj.minCostConnectPoints(points));
    }
}
