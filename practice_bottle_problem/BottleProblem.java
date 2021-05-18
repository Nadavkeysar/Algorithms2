package practice_bottle_problem;

import java.util.Arrays;

public class BottleProblem {

    public static int index(int i, int j, int n) {
        return (n + 1) * i + j;
    }


    public static boolean[][] bottleProblem(int m, int n) {

        int dim = (m + 1) * (n + 1);
        boolean[][] mat = new boolean[dim][dim];

        for(int i = 0 ; i < dim; i ++) {
            for(int j = 0; j < dim; j++) {
                mat[i][j] = false;
            }
        }

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                int ind = index(i, j, n);

                // empty
                mat[ind][index(i,0,n)] = true;
                mat[ind][index(0, j, n)] = true;

                // file
                mat[ind][index(i, n ,n)] = true;
                mat[ind][index(m, j, n)] = true;

                // pass
                mat[ind][index(Math.max(0, i+j-n), Math.min(n, i+j), n)] = true;
                mat[ind][index(Math.min(m, i+j), Math.max(0, i+j-m), n)] = true;
            }
        }
        return mat;
    }

    public static void printBooleanMatrix(boolean[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    public static void main(String[] args) {
        printBooleanMatrix(bottleProblem(1, 2));
    }
}
