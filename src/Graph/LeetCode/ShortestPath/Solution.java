package Graph.LeetCode.ShortestPath;

import java.util.*;


public class Solution {
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        
        //{a, b, w}, denoting there is an edge between a and b, and w is the weight of that edge.

        // Initiation of Parent
        int[] parent = new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i]=-1;
        }

        int[] distanceArray = new int[n+1];
        Arrays.fill(distanceArray,Integer.MAX_VALUE);

        // Choose Source Element
        int source = 1;
        int distance = 0;
        distanceArray[source] = 0;


        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)-> Integer.compare(a[1],b[1]));
        queue.add(new int[]{source,distance});
        List<Integer> result = new ArrayList<>();

        while(!queue.isEmpty()){
            int[] getFromQueue =  queue.poll();
            int node = getFromQueue[0];
            int distanceFromNode = getFromQueue[1];
            for( int[] arr : edges){
                if(arr[0]==node){
                    int goingNode = arr[1];
                    int newDistance = distanceFromNode + arr[2];
                    if(newDistance < distanceArray[goingNode]){
                        distanceArray[goingNode] = newDistance;
                        queue.offer(new int[]{goingNode,newDistance});
                        parent[goingNode] = node;
                    }

                }
            }

        }

        if(distanceArray[n]== Integer.MAX_VALUE){
            return new ArrayList<>(List.of(-1));
        }


        int x = parent[n];
        result.add(n);
        while(x!=1){
            result.add(x);
            x=parent[x];
        }
        result.add(1);
        result.add(distanceArray[n]);
        Collections.reverse(result);

        return result;

    }
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] array = {
                {2, 5, 6},
                {1, 4, 4},
                {3, 6, 1},
                {4, 5, 5},
                {1, 2, 2}
        };
        System.out.println(s.shortestPath(6,5,array));
    }
}
