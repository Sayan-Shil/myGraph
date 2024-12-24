package Graph.LeetCode.PathWithMinimumEfort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Solution {
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;

        int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};
        int[][] result = new int[row][col];
        for(int[] num : result){
            Arrays.fill(num,Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)-> Integer.compare(a[2],b[2]));
        result[0][0]=0;
        queue.offer(new int[]{0,0,0});

        while(!queue.isEmpty()){
            int[] popArray = queue.poll();
            int x= popArray[0];
            int y = popArray[1];
            int diff = popArray[2];

            if(x== row-1 && y== col-1){
                return diff;
            }

            for(int[] dir : direction){
               int X = x + dir[0];
               int Y = y + dir[1];

               if(X >=0 && X<row && Y>=0 && Y<col){
                   int abs = Math.abs(heights[x][y]-heights[X][Y]);
                   int maxDistance = Math.max(abs,diff);
                   if(maxDistance < result[X][Y]){
                       result[X][Y]= maxDistance;
                       queue.offer(new int[]{X,Y,maxDistance});
                   }
               }
            }
        }


        return  -1;

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] array = {
                {1, 2, 3},
                {3, 8, 4},
                {5, 3, 5}
        };
        System.out.println(s.minimumEffortPath(array));
    }
}
