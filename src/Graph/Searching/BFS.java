package Graph.Searching;


import java.util.*;

class BFS {
    // Function to return a list containing the BFS traversal of the graph.
    public ArrayList<Integer> BFSOfGraph(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        HashMap<Integer,ArrayList<Integer>> hm = new HashMap<>();
        final int[] i = {0};
        adj.forEach(al -> {
            hm.put(i[0], al);
            i[0]++;
        });

        ArrayList<Boolean> check = new ArrayList<>(Collections.nCopies(adj.size(), Boolean.FALSE));
        ArrayList<Integer> result = new ArrayList<>();
        BFS(hm, 0,check, result);
        return result;
    }

    private void BFS(HashMap<Integer,ArrayList<Integer>> hm, int source, ArrayList<Boolean> check, ArrayList<Integer> result) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        check.set(source,Boolean.TRUE);

        while(!queue.isEmpty()){
            int newsource = queue.poll();
            result.add(newsource);
            hm.get(newsource).forEach(i->{
                if(check.get(i)==Boolean.FALSE){
                    queue.offer(i);
                    check.set(i, Boolean.TRUE);
                }
            });
        }
    }

    public static void main(String[] args) {
        BFS s = new BFS();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(2,3,1)));
        adj.add(new ArrayList<>(List.of(0)));
        adj.add(new ArrayList<>(Arrays.asList(4,0)));
        adj.add(new ArrayList<>(List.of(0)));
        adj.add(new ArrayList<>(List.of(2)));

        ArrayList<Integer> al = s.BFSOfGraph(adj);
        System.out.println(al);
    }
}