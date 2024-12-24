package Graph.Sorting;

import java.util.*;

public class TopologicalSorting {
    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        // Your code here
        HashMap<Integer,ArrayList<Integer>> hm = new HashMap<>();
        final int[] p = {0};
        adj.forEach(al -> {
            hm.put(p[0], al);
            p[0]++;
        });

        boolean[] visited = new boolean[hm.size()];
        Arrays.fill(visited,false);
        ArrayList<Integer> al = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for(int i=0;i<hm.size();i++){
            if(!visited[i]){
                DFS(hm,i,visited,stack);
            }
        }
        while (!stack.isEmpty()) {
            al.add(stack.pop());
        }
        return al;

    }

    private static void DFS(HashMap<Integer, ArrayList<Integer>> hm, int i, boolean[] visited, Stack<Integer> stack) {
        visited[i] = true;
        for( Integer node : hm.get(i)){
            if(!visited[node]){
                visited[node] =true;
                DFS(hm,node,visited,stack);

            }
        }
        stack.push(i);

    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(3,2)));
        adj.add(new ArrayList<>(List.of(4)));
        adj.add(new ArrayList<>(List.of(3,1)));
        adj.add(new ArrayList<>(List.of(1)));
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>(List.of(1,4)));
        System.out.println(topologicalSort(adj));


    }
}
