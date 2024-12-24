package Graph.LeetCode.NetworkDelayTime;

import java.util.*;
import java.util.function.Predicate;

public class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, List<int[]>> hm = new HashMap<>();
        for (int[] arr : times) {
            hm.computeIfAbsent(arr[0], i -> new ArrayList<>()).add(new int[]{arr[1], arr[2]});
        }
        int[] result = new int[n+1];
        Arrays.fill(result,Integer.MAX_VALUE);

        int source = k;
        result[source]=0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)-> Integer.compare(a[1],b[1]));
        minHeap.offer(new int[]{source,0});

        while(!minHeap.isEmpty()){
            int[] list = minHeap.poll();
            int node = list[0];
            int distance = list[1];
            if(hm.get(node) != null){
                for(int[] num : hm.get(node)){
                    int newDestination = num[0];
                    int newDistance = distance  + num[1];

                    if( newDistance < result[newDestination]){
                        result[newDestination] = newDistance;
                        minHeap.offer(new int[]{newDestination,newDistance});
                    }

                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (result[i] == Integer.MAX_VALUE) return -1; // Unreachable node
            maxTime = Math.max(maxTime, result[i]);
        }

        return maxTime;

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] array = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };

        System.out.println(s.networkDelayTime(array,4,2));
    }

}