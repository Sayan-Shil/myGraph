package Graph.LeetCode.CourceSchedule02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class CourseScheduleWithDFS2 {
    public int[] canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        // Build the graph

        for (int[] prerequisite : prerequisites) {
            int key = prerequisite[1];
            int value = prerequisite[0];
            hm.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        boolean[] visited=new boolean[numCourses];
        boolean[] recursionStack = new boolean[numCourses];
        Arrays.fill(visited,false);
        Arrays.fill(recursionStack,false);

        for (int i=0;i<numCourses;i++){
            if (!visited[i]){
                if (topowithDFS(hm, i, stack, visited, recursionStack)) {
                    return new int[] {}; // Cycle detected, return empty array
                }
            }
        }

        int index=0;
        int[] result= new int[numCourses];

        while(!stack.isEmpty()){
            result[index]= stack.pop();
            index++;
        }
        return result;


    }

    private boolean topowithDFS(HashMap<Integer, ArrayList<Integer>> hm, int i, Stack<Integer> stack, boolean[] visited, boolean[] recursionStack) {
        if (recursionStack[i]) {
            return true;
        }
        visited[i]= true;
        recursionStack[i]=true;
        if(hm.containsKey(i)){
            for(Integer i1: hm.get(i)){
                if(recursionStack[i1]){
                    return true;
                }
                if(!visited[i1] && topowithDFS(hm,i1,stack,visited,recursionStack) ){
                    return true;
                }
            }
        }

        stack.push(i);
        recursionStack[i]=false;
        return false;
    }


    public static void main(String[] args) {
        CourseScheduleWithDFS2 c1 = new CourseScheduleWithDFS2();
        int[] play= c1.canFinish(3, new int[][]{{1,0},{0,1}}); // Output: true
        int[] play2=c1.canFinish(4, new int[][]{{1, 0}, {2,0}, {3,1}, {3,2}}); // Output: false

        Arrays.stream(play).forEach(System.out::print);
        System.out.println();
        Arrays.stream(play2).forEach(System.out::print);
        System.out.println(play.length>0);
        System.out.println(play2.length>0);
    }
}
