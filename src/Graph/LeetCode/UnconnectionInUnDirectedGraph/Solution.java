package Graph.LeetCode.UnconnectionInUnDirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public long countPairs(int n, int[][] edges) {
        int[] rank = new int[n];
        int[] parent = new int[n];

        for(int i=0;i<n;i++){
            rank[i]=0;
            parent[i]=i;
        }
        for( int[] arr : edges){
            int parent1= findParent(arr[0],parent);
            int parent2= findParent(arr[1],parent);

            if(parent1!=parent2){
                unionTwoSet(parent1,parent2,parent,rank);
            }
        }
        for (int i = 0; i < n; i++) {
            parent[i] = findParent(i, parent);
        }

        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = parent[i];
            hm.put(root, hm.getOrDefault(root, 0) + 1);
        }

        long result = 0;
        long remainingNodes = n;
        for (int size : hm.values()) {
            result += (long) size * (remainingNodes - size);
            remainingNodes -= size;
        }

        return result;
    }

    private void unionTwoSet(int num1, int num2, int[] parent, int[] rank) {
        int root1 = findParent(num1, parent);
        int root2 = findParent(num2, parent);

        if (root1 != root2) {
            if (rank[root1] == rank[root2]) {
                parent[root1] = root2;
                rank[root2]++;
            } else if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else {
                parent[root1] = root2;
            }
        }
    }

    private int findParent(int num, int[] parent) {
        if(num==parent[num]){
            return num;
        }
        else{
            return parent[num]= findParent(parent[num],parent);
        }
    }

    public static void main(String[] args) {

        Solution b= new Solution();

        int[][] edges = {
                {5, 0},
                {1, 0},
                {10, 7},
                {9, 8},
                {7, 2},
                {1, 3},
                {0, 2},
                {8, 5},
                {4, 6},
                {4, 2}
        };

        System.out.println(b.countPairs(11,edges));


    }
}