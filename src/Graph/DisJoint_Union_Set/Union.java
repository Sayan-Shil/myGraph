package Graph.DisJoint_Union_Set;

public class Union {
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
}
