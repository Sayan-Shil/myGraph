package Graph.LeetCode.CourseSchedule;

import java.util.*;

public class CourseScheduleWithBFS {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        // Build the graph
        for (int[] prerequisite : prerequisites) {
            int key = prerequisite[1];
            int value = prerequisite[0];
            hm.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        // Create the in-degree array
        int[] inDegree = new int[numCourses];
        Arrays.fill(inDegree, 0);
        for (int key : hm.keySet()) {
            for (int neighbor : hm.get(key)) {
                inDegree[neighbor]++;
            }
        }

        // Detect sources or destinations
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        // Perform BFS (Topological Sorting)
        while (!q.isEmpty()) {
            int current = q.remove();
            result.add(current);

            // Reduce the in-degree of neighbors
            if (hm.containsKey(current)) {
                for (Integer neighbor : hm.get(current)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        q.add(neighbor);
                    }
                }
            }
        }

        // If the topological sort includes all courses, we can finish
        return result.size() == numCourses;
    }

    public static void main(String[] args) {
        CourseScheduleWithBFS c1 = new CourseScheduleWithBFS();
        System.out.println(c1.canFinish(2, new int[][]{{1, 0}})); // Output: true
        System.out.println(c1.canFinish(2, new int[][]{{1, 0}, {0, 1}})); // Output: false
        System.out.println(c1.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}})); // Output: true
    }
}
