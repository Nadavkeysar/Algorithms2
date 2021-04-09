package exercise1;

public class BottleBoolean {

    /**
     * the matrix initialization
     * @param m
     * @param n
     * @return
     */
    public static boolean[][] initBooleanMatrixBottle(int m, int n) {
        int dimension = (m+1)*(n+1);
        boolean[][] mat = new boolean[dimension][dimension];

        // fill main diagonal
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                mat[i][j] = false;
            }
        }

        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                int ind = index(i, j, n);

                // empty
                mat[ind][index(0, j, n)] = true;
                mat[ind][index(i, 0, n)] = true;

                // fill
                mat[ind][index(i, n, n)] = true;
                mat[ind][index(m, j, n)] = true;

                // fill from one to another
                int i1 = index(Math.max(0, i+j-n), Math.min(n, i+j), n);
                mat[ind][i1] = true;

                i1 = index(Math.min(m, i+j), Math.max(0, i+j-m), n);
                mat[ind][i1] = true;
            }
        }
        for(int j = 0; j < dimension; j++) {
            mat[j][j] = false;
        }
        return mat;
    }

    /**
     * return the index in the matrix
     * @param i
     * @param j
     * @param n
     * @return
     */
    private static int index(int i, int j, int n) {
        return (n + 1) * i + j;
    }

    /**
     * print given boolean matrix
     * @param mat
     */
    public static void printMatrix(boolean[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            System.out.print("[");
            for(int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j]);
                if(j != mat[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        boolean mat[][] = initBooleanMatrixBottle(1, 2);
        printMatrix(mat);
    }

}
