package practice;

public class GraphMatrix implements graph_matrix {

    private int[][] mat;

    /**
     * constructor
     * @param n
     * @param m
     */
    public GraphMatrix(int n, int m) {
        this.mat = new int[n][m];
    }


    /**
     * to string method
     * @return
     */
    @Override
    public String toString() {
        String ans = "";
        for(int i = 0; i < this.mat.length; i++) {
            ans += "[";
            for(int j = 0; j < this.mat[0].length; j++) {
                ans += this.mat[i][j];
                if(j != this.mat[0].length - 1) ans += ", ";
            }
            ans += "]\n";
        }
        return ans;
    }
}
