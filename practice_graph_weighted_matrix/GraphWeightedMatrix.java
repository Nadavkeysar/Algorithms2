package practice_graph_weighted_matrix;

import java.util.Arrays;

public class GraphWeightedMatrix implements weighted_graph_matrix {

    private final int infinity = Integer.MAX_VALUE;
    private int size;
    private int[][] mat;

    /**
     * constructor
     * @param n
     */
    public GraphWeightedMatrix(int n) {
        this.size = n;
        this.mat = new int[this.size][this.size];
        clear();
    }

    /**
     * copy constructor
     * @param other
     */
    public GraphWeightedMatrix(int[][] other) {
        this.size = other.length;
        this.mat = new int[this.size][this.size];
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                this.mat[i][j] = other[i][j];
            }
        }
    }

    @Override
    public int[][] copy() {
        int[][] newMatrix = new int[this.size][this.size];
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                newMatrix[i][j] = this.mat[i][j];
            }
        }
        return newMatrix;
    }

    @Override
    public void connect(int i, int j, int w) { this.mat[i][j] = this.mat[j][i] = w; }

    @Override
    public int getWeight(int i, int j) { return this.mat[i][j]; }

    @Override
    public void disconnect(int i, int j) { this.mat[i][j] = Integer.MAX_VALUE; }

    @Override
    public boolean isConnected(int i, int j) {
        if(this.mat[i][j] < Integer.MAX_VALUE) return true;
        else return false;
    }

    @Override
    public void floyd_warshall() {
        for(int k = 0; k < this.size; k++) {
            for(int i = 0; i < this.size; i++) {
                for(int j = 0; j < this.size; j++) {
                    if(this.mat[i][k] != infinity && this.mat[k][j] != infinity) {
                        this.mat[i][j] = Math.min(this.mat[i][j], this.mat[i][k] + this.mat[k][j]);
                    }
                }
            }
        }
    }

    @Override
    public String[][] path_matrix() {
        String[][] newMatrix = new String[this.size][this.size];
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                if(this.mat[i][j] != infinity) newMatrix[i][j] = " " + i + "->" + j + " ";
                else newMatrix[i][j] = "";
            }
        }

        // build matrix
        for(int k = 0; k < this.size; k++) {
            for(int i = 0; i < this.size; i++) {
                for(int j = 0; j < this.size; j++) {
                    if(this.mat[i][k] != infinity && this.mat[k][j] != infinity) {

                        this.mat[i][j] = Math.min(this.mat[i][j], this.mat[i][k] + this.mat[k][j]);
                        newMatrix[i][j] = newMatrix[i][k] + newMatrix[k][j];
                    }
                }
            }
        }

        return newMatrix;
    }

    @Override
    public int size() { return this.size; }

    @Override
    public void clear() {
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                this.mat[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public String toString() { return toString(this.mat); }

    /**
     * static function to get the print string of a given matrix
     * @param mat
     * @return
     */
    public static String toString(int[][] mat) {
        String ans = "\t";
        for(int i = 0; i < mat.length; i++) ans += i + "  ";
        ans += "\n";
        for(int i = 0; i < mat.length; i++) {
            ans += + i + "  [";
            for(int j = 0; j < mat[0].length; j++) {
                if(mat[i][j] != Integer.MAX_VALUE) ans += mat[i][j];
                else ans += "INF";
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
