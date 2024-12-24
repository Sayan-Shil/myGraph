package Graph.LeetCode.MakeEthernetConnection;

public class Solution {
    public int makeConnected(int n, int[][] connections) {
        int requiredWire =  n-1;
        if(connections.length< n-1){
            return -1;
        }

        int[] rank = new int[n];
        int[] parent= new int[n];
        for (int i = 0; i < n; i++) {
            rank[i]=0;
            parent[i]=i;
        }


        for( int[] arr : connections){
            int num1 = findParent( arr[0],parent);
            int num2 = findParent( arr[1],parent);
            if(num1 !=num2){
                unionTwoSet(num1,num2,parent,rank);
            }
        }

        int isolatedComputer = 0;
        for(int i=0;i<n;i++){
            if(parent[i]==i)
                isolatedComputer++;

        }

        return  isolatedComputer-1;


    }
    private void unionTwoSet(int num1, int num2, int[] parent, int[] rank) {


        if(rank[num1]==rank[num2]){
            parent[num1]=num2;
            rank[num2]++;
        }
        else if(rank[num1]>rank[num2]){
            parent[num2]=num1;
        }
        else{
            parent[num1]=num2;
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
        Solution s= new Solution();
        int[][] arr = new int[][]{
                {0,1},{0,2},{1,2}
        };

        int[][] arr2=new int[][]{{0,1},{0,2},{0,3},{1,2}};
        int[][] arr3 = new int[][]{{0,1},{0,2},{0,3},{1,2},{1,3}};
        int[][] arr4 = new int[][]{{1,5},{1,7},{1,2},{1,4},{3,7},{4,7},{3,5},{0,6},{0,1},{0,4},{2,6},{0,3},{0,2}};
        System.out.println(s.makeConnected(4,arr));
        System.out.println(s.makeConnected(6,arr2));
        System.out.println(s.makeConnected(6,arr3));
        System.out.println(s.makeConnected(12,arr4));
    }

}