package Graph.LeetCode.NumberOfProvinces;

import java.util.ArrayList;
import java.util.HashMap;

public class NumberOfProvincesWithDFS {
    public static int numberOfProvincesWithDFS(int[][] isConnected) {
        int result=0;
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        for(int i=0;i<isConnected.length;i++){
            for(int j=0;j<isConnected[i].length;j++){
                if(i==j){
                    if(!hm.containsKey(i)){
                        hm.put(i,new ArrayList<>());
                    }
                    continue;
                }
                if(isConnected[i][j]==1){
                    if(!hm.containsKey(i)){
                        ArrayList<Integer> al = new ArrayList<>();
                        al.add(j);
                        hm.put(i,al);
                    }
                    else{
                        hm.get(i).add(j);
                    }
                }
            }
        }

        boolean[] visited = new boolean[hm.size()];

        for(int i=0; i<hm.size();i++){
            if(!visited[i]){
                result++;
                callDFS(hm,i,visited);
            }
        }

        return result;
    }

    private static void callDFS(HashMap<Integer, ArrayList<Integer>> hm, int i, boolean[] visited) {
        visited[i]=true;
        for( Integer num : hm.get(i)){
            if(!visited[num]){
                callDFS(hm,num,visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(numberOfProvincesWithDFS(arr));





    }
}
