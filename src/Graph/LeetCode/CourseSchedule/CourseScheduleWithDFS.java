package Graph.LeetCode.CourseSchedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class CourseScheduleWithDFS {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Build the graph
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            int key = prerequisites[i][1];
            int value = prerequisites[i][0];

            hm.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recursionStack = new boolean[numCourses];
        Arrays.fill(visited, false);

        // Check each course
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (useDFS(hm, i, visited, recursionStack)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean useDFS(HashMap<Integer, ArrayList<Integer>> hm, int i, boolean[] visited, boolean[] recursionStack) {
        visited[i] = true;
        recursionStack[i] = true;

        if (hm.containsKey(i)) {
            for (Integer neighbor : hm.get(i)) {
                if (recursionStack[neighbor]) {
                    return true; // Cycle detected
                }
                if (!visited[neighbor] && useDFS(hm, neighbor, visited, recursionStack)) {
                    return true; // Cycle detected
                }
            }
        }

        recursionStack[i] = false;
        return false;
    }

    public static void main(String[] args) {
        CourseScheduleWithDFS c1 = new CourseScheduleWithDFS();
        System.out.println(c1.canFinish(2, new int[][]{{1, 0}})); // Output: true
        System.out.println(c1.canFinish(2, new int[][]{{1, 0}, {0, 1}})); // Output: false
    }
}
