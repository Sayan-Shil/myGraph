package Graph.LeetCode.Satisfiability_of_equality_equations;

public class Solution {
    public boolean equationsPossible(String[] equations) {
        int[] parent= new int[26];
        int[] rank = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i]=i;
            rank[i]=0;
        }

        for ( String s : equations){
            if ((s.charAt(1)=='=')){
                unionTwoSet(s.charAt(0)-'a', s.charAt(3)-'a',parent,rank);
            }
        }
        for(String s: equations){
            if ((s.charAt(1)=='!')){
                int i1 = findParent((int)s.charAt(1)-'a', parent);
                int i2= findParent((int)s.charAt(3)-'a', parent);
                if(i1!=i2){
                    return false;
                }
            }
        }
        for (String s : equations) {
            if (s.charAt(1) == '!') {
                int i1 = findParent(s.charAt(0) - 'a', parent);  // Corrected here
                int i2 = findParent(s.charAt(3) - 'a', parent);  // Corrected here
                if (i1 == i2) {
                    return false;
                }
            }
        }

        return true;

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

    }

}
