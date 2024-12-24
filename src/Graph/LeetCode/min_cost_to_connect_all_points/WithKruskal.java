package Graph.LeetCode.min_cost_to_connect_all_points;

import java.util.PriorityQueue;

public class WithKruskal {
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)-> Integer.compare(a[2],b[2]));
        int V = points.length;
        int[] parent = new int[V];
        int[] rank = new int[V];

        for(int i=0;i<V;i++){
            parent[i]=i;
            rank[i]=0;
        }

        for(int i=0;i<V;i++){
            for(int j=i+1;j<V;j++){
                int x1= points[i][0];
                int x2 = points[j][0];
                int y1 = points[i][1];
                int y2= points[i][1];

                int distance = Math.abs(x1-x2) + Math.abs(y1-y2);
                minHeap.offer(new int[]{i,j,distance});

            }
        }

        int sum=0;

       while(!minHeap.isEmpty()){
           int[] list = minHeap.poll();
           int source = list[0];
           int node = list[1];
           int distance = list[2];

           int parent_x = findParent(source,parent);
           int parent_y = findParent(node,parent);

           if(parent_x != parent_y){
               Union(parent_x,parent_y,parent,rank);
               sum += distance;
           }

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
}
