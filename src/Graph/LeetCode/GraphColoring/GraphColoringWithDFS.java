package Graph.LeetCode.GraphColoring;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphColoringWithDFS {
    public int[] graphColoring(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> colorSeparation = new ArrayList<>();
        int[] color= new int[adj.size()];
        Arrays.fill(color,-1);

        for(int i=0;i<adj.size();i++){
            if(color[i]==-1){
               if(!colorWithDFS(adj,i,color,1)){
                  return new int[]{};
               }
            }
        }

        return color;

    }

    private boolean colorWithDFS(ArrayList<ArrayList<Integer>> adj, int currentNode, int[] color, int colorcode) {
        color[currentNode]=colorcode;
        if(adj.get(currentNode) !=null){
            for(Integer newNode : adj.get(currentNode)){
                if(color[newNode]== color[currentNode]){
                    return false;
                }
                if(color[newNode]==-1){
                    if(!colorWithDFS(adj,newNode,color,1-color[currentNode])){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        GraphColoringWithDFS b = new GraphColoringWithDFS();
        ArrayList<ArrayList<Integer>> adj4 = new ArrayList<>();
        adj4.add(new ArrayList<>(Arrays.asList(1))); // Node 0 connects to 1
        adj4.add(new ArrayList<>(Arrays.asList(0, 2))); // Node 1 connects to 0 and 2
        adj4.add(new ArrayList<>(Arrays.asList(1))); // Node 2 connects to 1
        adj4.add(new ArrayList<>(Arrays.asList(4, 5))); // Node 3 connects to 4 and 5
        adj4.add(new ArrayList<>(Arrays.asList(3))); // Node 4 connects to 3
        adj4.add(new ArrayList<>(Arrays.asList(3))); // Node 5 connects to 3


        int[] color =  b.graphColoring(adj4);
        if(color.length==0){
            System.out.println("Not BiPartite");
        }
        else{
            int index=0;
            for(int i: color){
                if(i==0){
                    System.out.println("Red Color -->" +index);
                    index++;
                }
                else{
                    System.out.println("Yellow Color-->" +index);
                    index++;
                }
            }
        }
    }
}
