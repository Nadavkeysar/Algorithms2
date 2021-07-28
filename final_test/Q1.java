package algorithms_1.final_test;

public class Q1 {
    public static int lcs(int[] X, int[] Y) {

        // X and Y lengths
        int n = X.length, m = Y.length;

        // build the matrix for the dynamic programming
        int[][] f = new int[n + 1][m + 1];

        // left column and top row are zeros
        for (int i = 0; i < n + 1; i++) f[i][0] = 0;
        for (int j = 0; j < m + 1; j++) f[0][j] = 0;

        // build the rest of the matrix
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {

                // definition of the dynamic functions
                if (X[i - 1] == Y[j - 1]) f[i][j] = 1 + f[i - 1][j - 1];
                else f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
            }
        }

        // size of the LCS
        return f[n][m];
    }


    public static int lcs3(int[] X, int[] Y, int[] Z) {

        // X, Y ans Z lengths
        int m = X.length, n = Y.length, o = Z.length;

        // build the 3D matrix for the dynamic programming
        int[][][] L = new int[m + 1][n + 1][o + 1];

        // build the rest of the matrix
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= o; k++) {

                    // left column and top row are zeros
                    if (i == 0 || j == 0 || k == 0) L[i][j][k] = 0;

                    // definition of the dynamic functions
                    else if (X[i - 1] == Y[j - 1] && X[i - 1] == Z[k - 1]) L[i][j][k] = L[i - 1][j - 1][k - 1] + 1;
                    else L[i][j][k] = Math.max(Math.max(L[i - 1][j][k], L[i][j - 1][k]), L[i][j][k - 1]);
                }
            }
        }

        // size of the LCS
        return L[m][n][o];
    }
}