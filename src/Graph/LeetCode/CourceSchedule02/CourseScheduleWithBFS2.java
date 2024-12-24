package Graph.LeetCode.CourceSchedule02;

import java.util.*;

public class CourseScheduleWithBFS2 {
    public int[] canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();

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

        return toposort(hm,numCourses,inDegree);


    }

    private int[] toposort(HashMap<Integer, ArrayList<Integer>> hm, int numCourses, int[] inDegree) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }


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

        if(result.size()==numCourses){
            int[] result2 = new int[numCourses];
            int[] array = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
                array[i] = result.get(i);
            }
            return array;
        }
        else {
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        CourseScheduleWithBFS2 c1 = new CourseScheduleWithBFS2();
        System.out.println(c1.canFinish(2, new int[][]{{1, 0}})); // Output: true
        System.out.println(c1.canFinish(2, new int[][]{{1, 0}, {0, 1}})); // Output: false
        System.out.println(c1.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}})); // Output: true
    }
}
