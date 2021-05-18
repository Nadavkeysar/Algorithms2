package practice_graph_boolean_matrix;

import java.util.Arrays;

public class GraphBooleanMatrix implements boolean_graph_matrix {

    private boolean[][] mat;
    private int size;

    /**
     * constructor
     * @param n
     */
    GraphBooleanMatrix(int n) {
        this.size = n;
        this.mat = new boolean[this.size][this.size];
    }

    /**
     * copy constructor
     * @param oldMatrix
     */
    GraphBooleanMatrix(boolean[][] oldMatrix) {
        this.size = oldMatrix.length;
        this.mat = new boolean[this.size][this.size];
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                this.mat[i][j] = oldMatrix[i][j];
            }
        }
    }

    /**
     * making the main diagonal be false value
     */
    private void diagonal_default() {
        for(int i = 0; i < this.size; i++) this.mat[i][i] = false;
    }

    /**
     * return a deep copy of the matrix
     * @return
     */
    @Override
    public boolean[][] copy() {
        boolean[][] newMatrix = new boolean[this.size][this.size];
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) newMatrix[i][j] = this.mat[i][j];
        }
        return newMatrix;
    }

    /**
     * connect between two nodes
     * @param i
     * @param j
     */
    @Override
    public void connect(int i, int j) { this.mat[i][j] = true; this.mat[j][i] = true; }

    /**
     * disconnect between two nodes
     * @param i
     * @param j
     */
    @Override
    public void disconnect(int i, int j) { this.mat[i][j] = false; }

    /**
     * return true if two nodes are connected
     * @param i
     * @param j
     * @return
     */
    @Override
    public boolean isConnected(int i, int j) { return this.mat[i][j]; }

    /**
     * run floyd-warshall algorithm on the matrix
     */
    @Override
    public void floyd_warshall() {
        for(int k = 0; k < this.size; k++) {
            for(int i = 0; i < this.size; i++) {
                for(int j = 0; j < this.size; j++) {
                    this.mat[i][j] = this.mat[i][j] || (this.mat[i][k] && this.mat[k][j]);
                }
            }
        }
    }

    /**
     * return a path matrix
     * @return
     */
    @Override
    public String[][] path_matrix() {
        // path matrix
        String[][] newMatrix = new String[this.size][this.size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(this.mat[i][j]) newMatrix[i][j] = " " + i + "->" + j + " ";
                else newMatrix[i][j] = "";
            }
        }

        // path matrix building
        for(int k = 0; k < size; k++) {
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if(!this.mat[i][j] && (this.mat[i][k] && this.mat[k][j])) {
                        newMatrix[i][j] = newMatrix[i][k] + newMatrix[k][j];
                    }
                    this.mat[i][j] = this.mat[i][j] || (this.mat[i][k] && this.mat[k][j]);
                }
            }
        }
        return newMatrix;
    }

    /**
     * return the number of nodes in the graph
     * @return
     */
    @Override
    public int size() { return this.size; }

    /**
     * clear the matrix to false
     */
    @Override
    public void clear() {
        for(int i = 0; i < this.size; i++) for(int j = 0; j < this.size; j++) this.mat[i][j] = false;
    }

    /**
     * to string method
     * @return
     */
    @Override
    public String toString() { return toString(this.mat); }

    /**
     * static function to get the print string of a given matrix
     * @param mat
     * @return
     */
    public static String toString(boolean[][] mat) {
        String ans = "\t";
        for(int i = 0; i < mat.length; i++) ans += i + "  ";
        ans += "\n";
        for(int i = 0; i < mat.length; i++) {
            ans += + i + "  [";
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j]) ans += "T";
                else ans += "F";
                if(j != mat[0].length - 1) ans += ", ";
            }
            ans += "]\n";
        }
        return ans;
    }

    /**
     * static function to get the print string of a given matrix
     * @param mat
     * @return
     */
    public static String toString(String[][] mat) {
        String ans = "";
        for(int i = 0; i < mat.length; i++) {
            ans += Arrays.toString(mat[i]) + "\n";
        }
        return ans;
    }
}
