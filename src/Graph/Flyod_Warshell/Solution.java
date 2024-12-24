package Graph.Flyod_Warshell;

public class Solution {
    public void shortestDistance(int[][] mat) {
        // Code here
        int Vertices = mat.length;
        int col = mat[0].length;

        for(int j=0; j<Vertices;j++){
            for(int k=0;k<col;k++){
                if(mat[j][k]== -1){
                    mat[j][k] = 10000;
                }
            }
        }

        for(int i=0;i<Vertices;i++){
            for(int j=0; j<Vertices;j++){
                for(int k=0;k<col;k++){
                    int viaVertice = mat[j][i] + mat[i][k];
                    mat[j][k]= Math.min(mat[j][k],viaVertice);
                }
            }
        }

        for(int j=0; j<Vertices;j++){
            for(int k=0;k<col;k++){
                if(mat[j][k]== 10000){
                    mat[j][k] = -1;
                }
            }
        }
    }
}
