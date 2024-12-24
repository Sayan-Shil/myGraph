package Graph.CycleDetection.Directed;
import javax.crypto.spec.PSource;
import java.util.*;

public class CycleDetectionWithDFS

{
    public static boolean cycleDetectionWithDFS
(HashMap<Integer, ArrayList<Integer>> hm) {
        boolean[] visited= new boolean[hm.size()];
        boolean[] recursionStack = new boolean[hm.size()];
        Arrays.fill(visited,false);
        Arrays.fill(recursionStack,false);

        for(int i=0; i<hm.size();i++){
            if (!visited[i]){
                if (cycleDetect(hm, i, visited, recursionStack)) {
                    return true;  // Cycle detected
                }
            }
        }
        return false;
    }

    private static boolean cycleDetect(HashMap<Integer, ArrayList<Integer>> hm, int source, boolean[] visited, boolean[] recursionStack) {
        visited[source] = true;
        recursionStack[source]=true;
        for( Integer i: hm.get(source)){
            if(recursionStack[i]){
                return true;
            }
            if(!visited[i] && cycleDetect(hm,i,visited,recursionStack)){
                return true;
            }
        }

        recursionStack[source]=false;
        return false;

    }


    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        hm.put(0,new ArrayList<>(List.of(1)));
        hm.put(1,new ArrayList<>(List.of(2)));
        hm.put(2,new ArrayList<>(List.of(0)));

        System.out.println(cycleDetectionWithDFS(hm));
    }
}
