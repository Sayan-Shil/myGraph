package Graph.Kosaraju;

import java.net.Inet4Address;
import java.util.*;

public class Solution {
    public static int kosaraju(List<List<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];
        Stack<Integer> stack = new Stack<>();

        // Make Order of DFS with Stack
        for(int i=0; i<adj.size();i++){
            if(!visited[i]){
                firstDFS(adj,i,visited,stack);
            }
        }

        // Make Reverse Graph - Transversal Graph

        HashMap<Integer,ArrayList<Integer>> hm=new HashMap<>();
        for(int i=0;i<adj.size();i++){

            if(adj.get(i)!=null){
                for( Integer num: adj.get(i)){
                    hm.computeIfAbsent(num, number-> new ArrayList<>()).add(i);
                }
            }
        }

        // Do DFS on the order of stack

        boolean[] visitedReverse = new boolean[adj.size()];
        int scg=0;
        while(!stack.isEmpty()){
            int num = stack.pop();
            if(!visitedReverse[num]){
                secondBFS(hm,num,visitedReverse);
                scg++;
            }
        }
        return scg;


    }

    private static void secondBFS(HashMap<Integer, ArrayList<Integer>> hm, int num, boolean[] visitedReverse) {
        visitedReverse[num]=true;
        if(hm.get(num)!=null){
            for(Integer node : hm.get(num)){
                if(!visitedReverse[node]){
                    secondBFS(hm,node,visitedReverse);
                }
            }
        }
    }

    private static void firstDFS(List<List<Integer>> adj, int i, boolean[] visited, Stack<Integer> stack) {
        visited[i]=true;
        if(adj.get(i)!=null){
            for(Integer num : adj.get(i)){
                if(!visited[num]){
                    firstDFS(adj,num,visited,stack);
                }
            }
        }
        stack.push(i);

    }

    public static void main(String[] args) {
        List<List<Integer>> graph2 = Arrays.asList(
                Arrays.asList(1, 2), // 0 -> 1, 0 -> 2
                Arrays.asList(2, 3), // 1 -> 2, 1 -> 3
                Arrays.asList(0),    // 2 -> 0
                Arrays.asList()      // 3 has no outgoing edges
        );
        System.out.println(kosaraju(graph2));
    }
}
