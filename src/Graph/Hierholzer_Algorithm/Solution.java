package Graph.Hierholzer_Algorithm;

import java.util.*;

public class Solution {
    public int[][] validArrangement(int[][] pairs) {
        HashMap<Integer, ArrayList<Integer>> hm=new HashMap<>();
        for( int[] arr : pairs){
            hm.computeIfAbsent(arr[0], num -> new ArrayList<>()).add(arr[1]);
        }
        int[] inDegree = new int[hm.size()];
        int[] outDegree = new int[hm.size()];

        hm.forEach((key, value) -> {
            outDegree[key] += value.size();
            for (Integer neighbor : value) {
                inDegree[neighbor]++;
            }
        });

        int sourceNode=pairs[0][0];
        for (int i = 0; i < hm.size(); i++) {
            if(outDegree[i]-inDegree[i]==1){
                sourceNode=i;
                break;
            }
        }

        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        stack.push(sourceNode);

        while(!stack.isEmpty()){
            int num = stack.peek();
            if(!hm.get(num).isEmpty() && hm.containsKey(num)){
                int node = hm.get(num).remove(hm.size()-1);
                stack.push(node);
            }
            else{
                list.add(num);
                stack.pop();
            }
        }

        int[][] result = new int[list.size()-1][2];
        for(int i=0; i<list.size()-1;i++){
            result[i][0]= list.get(i);
            result[i][1]= list.get(i+1);

        }

return result;

    }

}
