package practice_floyd_warshall;

import java.util.Arrays;

public class FloydWarshall {

    private static final int infinity = Integer.MAX_VALUE;

    /**
     * floyd warshall algorithm on boolean matrix
     * @param mat
     */
    public static void floyd_warshall(boolean[][] mat) {

        // o(n^3)
        for(int k = 0; k < mat.length; k++) {
            for(int i = 0; i < mat.length; i++) {
                for(int j = 0; j < mat.length; j++) {
                    mat[i][j] = mat[i][j] || (mat[i][k] && mat[k][j]);
                }
            }
        }
    }

    /**
     * floyd warshall algorithm on integers matrix
     * @param mat
     */
    public static void floyd_warshall(int[][] mat) {

        // O(n^3)
        for(int k = 0; k < mat.length; k++) {
            for(int i = 0; i < mat.length; i++) {
                for(int j = 0; j < mat.length; j++) {
                    if(mat[i][k] != infinity && mat[k][j] != infinity) {
                        mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                    }
                }
            }
        }
    }

    /**
     * isConnected of boolean matrix
     * complexity = O(n^2)
     * @param mat
     * @return
     */
    public static boolean isConnectedO2(boolean[][] mat) {
        floyd_warshall(mat);
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat.length; j++) {
                if(!mat[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * isConnected of boolean matrix
     * complexity = O(n^2)
     * @param mat
     * @return
     */
    public static boolean isConnectedO1(boolean[][] mat) {
        floyd_warshall(mat);
        for(int i = 0; i < mat.length; i++) {
            if(!mat[0][i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * return the number of connected components in a matrix
     * @param mat
     * @return
     */
    public static int numberOfConnectedComponents(boolean[][] mat) {
        int counter = 0;
        int[] arr = new int[mat.length];
        for(int i = 0; i < mat.length; i++) {
            if(arr[i] == 0) arr[i] = ++counter;
            for(int j = i + 1; j < mat.length; j++) {
                if(mat[i][j] && arr[j] == 0) {
                    arr[j] = counter;
                }
            }
        }
        return counter;
    }

    /**
     * return true if the graph matrix contains negative circle
     * @param mat
     * @return
     */
    public static boolean isThereNegativeCircle(int[][] mat) {
        floyd_warshall(mat);
        return findNegativeDiagonal(mat);
    }

    private static boolean findNegativeDiagonal(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            if(mat[i][i] < 0) return true;
        }
        return false;
    }

    /**
     * print boolean matrix
     * @param mat
     */
    public static void printMatrix(boolean[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    /**
     * print integers matrix
     * @param mat
     */
    public static void printMatrix(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        int [][] mat = {{0,infinity,-3},{4,0,infinity},{2,infinity,0}};
        floyd_warshall(mat);
        printMatrix(mat);

    }


}
