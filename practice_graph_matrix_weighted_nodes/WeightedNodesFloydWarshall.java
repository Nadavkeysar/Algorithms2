package practice_graph_matrix_weighted_nodes;

import java.util.Arrays;

public class WeightedNodesFloydWarshall {

    private static final int infinity = Integer.MAX_VALUE;

    public static int[][] floyd_warshall_boolean(boolean[][] mat, int[] arr) {
        int size = arr.length;
        int[][] newMat = new int[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(mat[i][j]) {
                    newMat[i][j] = arr[i] + arr[j];
                } else {
                    newMat[i][j] = infinity;
                }

            }
        }

        for(int k = 0; k < size; k++) {
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if(newMat[i][k] != infinity && newMat[k][j] != infinity) {
                        newMat[i][j] = Math.min(newMat[i][j], newMat[i][k] + newMat[k][j]);
                    }
                }
            }
        }

        for(int i = 0; i < size; i++) {
            newMat[i][i] = 0;
        }



        return newMat;
    }

    public static void printMatrix(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    public static void main(String[] args) {
        boolean[][] mat = {
                {false, true, false, true},
                {true, false, true, false},
                {false, true, false, true},
                {true, false, true, false}
        };
        int[] arr = {4,3,2,5};
        printMatrix(floyd_warshall_boolean(mat, arr));
    }
}
