package algorithms_1.maraton2;

public class NumberGame {

    /**
     * using dynamic programming
     * f(i,j) = the best remainder between the first player (the one who play first) and the other player
     * when the array start in index of i and end in index of j -> i <= j
     *
     * f(i,i) = a[i]
     * f(i,j) = MAX(a[i] - f(i+1,j), a[j] - f(i,j-1))
     *
     * for example: [3, 6, 1, 4]
     * we get:
     * [3, 3, -2, 6]
     * [0, 6, 5, 3]
     * [0, 0, 1, 3]
     * [0, 0, 0, 4]
     *
     * complexity = O(n * n) = O(n ^ 2)
     *
     * @param arr
     * @return
     */

    public static int numberGame(int[] arr) {

        // get array length
        int n = arr.length;

        // build new matrix n*n
        int[][] m = new int[n][n];

        // main diagonal
        for(int i = 0; i < m.length; i++) m[i][i] = arr[i];


        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                m[i][j] = Math.max(arr[i] - m[i+1][j], arr[j] - m[i][j-1]);
            }
        }

        // remainder
        return m[0][n-1];
    }

    public static int numberGameCycle(int[] arr) { // O(n^3)
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int a = arr[i];
            int[] b = new int[n-1];
            int k = (i+1) % n;
            for (int j = 0; j < n-1; j++) {
                b[j] = arr[k];
                k = (k+1) % n;
            }
            int f = numberGame(b);
            if(a-f > max) max = a-f;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,6,1,3,6};
        System.out.println(numberGame(arr));
        System.out.println(numberGameCycle(arr));
    }
}
