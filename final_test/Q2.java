package algorithms_1.final_test;

public class Q2 {
    public static int longestOfOne(int[] arr) {

        // length of the array
        int n = arr.length;

        // create help array for dynamic programming
        int[] help = new int[n];

        // base case
        if(arr[0] == 1) help[0] = 1;

        // definition of the dynamic functions
        for(int i = 1; i < n; i++) {
            if(arr[i] != 0) help[i] = help[i-1] + 1;
        }

        // finding max number in help array
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            if(max < help[i]) max = help[i];
        }

        // return max
        return max;
    }

    public static int imaginaryPlus(int[][] mat) {

        // length and height of the matrix
        int n = mat.length;
        int m = mat[0].length;

        // definition of the dynamic functions
        int[][] u = new int[n][m];
        int[][] d = new int[n][m];
        int[][] l = new int[n][m];
        int[][] r = new int[n][m];
        for (int i = 0; i < m; i++) u[0][i] = mat[0][i];
        for (int i = 0; i < m; i++) d[n-1][i] = mat[n-1][i];
        for (int i = 0; i < n; i++) l[i][0] = mat[i][0];
        for (int i = 0; i < n; i++) r[i][m-1] = mat[i][m-1];

        // definition of the dynamic functions
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(mat[i][j] != 0) {
                    if(i != 0) u[i][j] = u[i-1][j] + 1;
                    if(j != 0) l[i][j] = l[i][j-1] + 1;
                }
            }
        }

        // definition of the dynamic functions
        for(int i = n-1; i >= 0; i--) {
            for(int j = m-1; j >= 0; j--) {
                if(mat[i][j] != 0) {
                    if(i != n-1) d[i][j] = d[i+1][j] + 1;
                    if(j != m-1) r[i][j] = r[i][j+1] + 1;
                }
            }
        }

        // found the best left, right, up and down (maximum)
        int left = 0, right = 0, up = 0, down = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(Math.min(Math.min(u[i][j], d[i][j]),Math.min(l[i][j], r[i][j])) > max) {
                    max = Math.min(Math.min(u[i][j], d[i][j]),Math.min(l[i][j], r[i][j]));
                    left = l[i][j];
                    right = r[i][j];
                    up = u[i][j];
                    down =  d[i][j];
                }
            }
        }

        // for each -1 because dont count the middle, and +1 in the end for the middle
        return (left - 1) + (right - 1) + (up - 1) + (down - 1) + 1;
    }
}
