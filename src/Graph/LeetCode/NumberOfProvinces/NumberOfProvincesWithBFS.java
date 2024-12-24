package Graph.LeetCode.NumberOfProvinces;

import java.util.*;

public class NumberOfProvincesWithBFS {
    public static int numberOfProvincesWithBFS(int[][] isConnected) {
        HashMap<Integer,ArrayList<Integer>> hm = new HashMap<>();

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length ; j++) {
                if(isConnected[i][j]==1){
                    if(hm.containsKey(i)){
                        hm.get(i).add(j);
                    }
                    else{
                        ArrayList<Integer> al = new ArrayList<>();
                        al.add(j);
                        hm.put(i,al);
                    }
                    if(hm.containsKey(j)){
                        hm.get(j).add(i);
                    }
                    else{
                        ArrayList<Integer> al = new ArrayList<>();
                        al.add(i);
                        hm.put(j,al);
                    }
                }
            }
        }

        boolean[] visited = new boolean[hm.size()];
        Arrays.fill(visited,false);
        int count=0;

        for (int i = 0; i < hm.size(); i++) {
            if(!visited[i]){
                useBFS(hm,i,visited);
                count++;
            }
        }
        return count;
    }

    private static void useBFS(HashMap<Integer, ArrayList<Integer>> hm, int i, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        visited[i]=true;

        while(!queue.isEmpty()){
            int currentNode= queue.poll();
            for(Integer node : hm.get(currentNode)){
                if(!visited[node]){
                    visited[node]=true;
                    useBFS(hm,node,visited);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[][] arr = new int[][] {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(numberOfProvincesWithBFS(arr));





    }
}
