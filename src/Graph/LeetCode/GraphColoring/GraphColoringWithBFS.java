package Graph.LeetCode.GraphColoring;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class GraphColoringWithBFS {
    public int[] graphColoring(ArrayList<ArrayList<Integer>> adj) {
        int[] color = new int[adj.size()];
        Arrays.fill(color,-1);

        for(int i=0;i<adj.size();i++){
            if(color[i]==-1){
                if(!checkBipartite(adj,i,color,1)){
                    return new int[]{};
                }
            }
        }


        return color;
    }

    private boolean checkBipartite(ArrayList<ArrayList<Integer>> adj, int currentNode, int[] color, int colorcode) {
        Queue<Integer> queue= new LinkedList<>();
        queue.offer(currentNode);
        color[currentNode] = colorcode;
        while(!queue.isEmpty()){
            int source= queue.remove();
            if(adj.get(source)!=null){
                for(Integer newNode : adj.get(source)){
                    if(color[newNode]==color[source]){
                        return false;
                    }
                    else if(color[newNode]==-1){
                        color[newNode] = 1 - color[source];;
                        queue.offer(newNode);
                    }
                }
            }
        }



        return true;
    }

    public static void main(String[] args) {
        GraphColoringWithBFS b=new GraphColoringWithBFS();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(1, 3))); // 0 -> 1, 3
        adj.add(new ArrayList<>(Arrays.asList(0, 2))); // 1 -> 0, 2
        adj.add(new ArrayList<>(Arrays.asList(1, 3))); // 2 -> 1, 3
        adj.add(new ArrayList<>(Arrays.asList(0, 2))); // 3 -> 0, 2

        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
        adj2.add(new ArrayList<>(Arrays.asList(1, 0))); // 0 -> 1, 0 (self-loop)
        adj2.add(new ArrayList<>(Arrays.asList(0)));    // 1 -> 0



        int[] color =  b.graphColoring(adj);
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

        Predicate<int[]> checkEmpty = i-> i.length!=0;
        Consumer<int[]> extractColor = arr -> {

            if (checkEmpty.test(arr)) {
                Arrays.stream(arr).forEach(i -> {
                    if ((Integer) i == 0) {
                        System.out.println("Red");
                    } else {
                        System.out.println("Blue");
                    }
                });
            } else {
                System.out.println("Not Bipartite !! Coloring Not Possible");
            }
        };

        extractColor.accept(color);








    }
}
