package algorithms_1.maraton1;

import java.util.Arrays;

public class Common {

    /**
     * print a given matrix function
     * @param mat
     */
    public static void printMatrix(int[][] mat) {
        for(int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }
}
