package Graph.Djistra_2D;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ShortestPathIn2DMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;

        if (grid[0][0] == 1 || grid[row - 1][column - 1] == 1) {
            return -1;
        }

        BiPredicate<Integer, Integer> isSafe = (a, b) -> (a >= 0 && a < row && b >= 0 && b < column && grid[a][b] == 0);

        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        // Make Result Array same like 1-D but in 2-D
        int[][] result = new int[row][column];
        for (int i = 0; i < row; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)-> Integer.compare(a[2],b[2]));
        result[0][0]= 0;
        queue.offer(new int[]{0,0,0});

        while(!queue.isEmpty()){
            int[] gridlist = queue.poll();
            int x = gridlist[0];
            int y= gridlist[1];
            int distance = gridlist[2];

            if(x == row-1 && y == column-1)
                return distance+1;

            for( int[] arr : direction){
                int x_ = x + arr[0];
                int y_ = y+ arr[1];

                if(isSafe.test(x_,y_)){
                    int newDistance = distance + 1;
                    if(newDistance < result[x_][y_]){
                        result[x_][y_] = newDistance;
                        queue.offer(new int[]{x_,y_,newDistance});
                    }
                }
            }
        }

        return -1; // If there's no path found

    }


    public static void main(String[] args) {
        ShortestPathIn2DMatrix s = new ShortestPathIn2DMatrix();
        int[][] matrix = {
                {0, 1},
                {1, 0}
        };
        System.out.println(s.shortestPathBinaryMatrix(matrix));
    }
}
