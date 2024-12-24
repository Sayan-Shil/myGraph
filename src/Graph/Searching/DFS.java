package Graph.Searching;

import java.util.*;

class DFS {
    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        HashMap<Integer,ArrayList<Integer>> hm = new HashMap<>();
        final int[] i = {0};
        adj.forEach(al -> {
            hm.put(i[0], al);
            i[0]++;
        });

        ArrayList<Boolean> check = new ArrayList<>(Collections.nCopies(adj.size(), Boolean.FALSE));
        ArrayList<Integer> result = new ArrayList<>();
        DFS(hm, 0,check, result);
        return result;
    }

    private void DFS(HashMap<Integer, ArrayList<Integer>> hm, int source, ArrayList<Boolean> check, ArrayList<Integer> result) {
        if(check.get(source)==Boolean.TRUE){
            return ;
        }

            check.set(source,Boolean.TRUE);
            result.add(source);

            for (int neighbor : hm.get(source)) {
                DFS(hm, neighbor, check, result);
            }

    }

    public static void main(String[] args) {
        DFS s = new DFS();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(2,3,1)));
        adj.add(new ArrayList<>(List.of(0)));
        adj.add(new ArrayList<>(Arrays.asList(4,0)));
        adj.add(new ArrayList<>(List.of(0)));
        adj.add(new ArrayList<>(List.of(2)));

        ArrayList<Integer> al = s.dfsOfGraph(adj);
        System.out.println(al);
    }
}