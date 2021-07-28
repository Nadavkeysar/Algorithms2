package algorithms_1.maraton1;

import java.util.Arrays;

// LIS = Longest Increasing Subsequence
// example:
// A = {5,2,9,7,8,5,3,2,1,10,6}
// LIS(A) = {5,7,8,10}
public class LIS {

    /**
     * dynamic function:
     * f(i) = the sub increasing sequence in length of i with the most optional numbers
     * base case:
     * f(0) = A[0]
     * every other item that we meet in the array, we will search where to insert him
     * our algorithm will search in the answer array with a binary search (always sorted)
     * the right place and replace the older item with the newest.
     * if the new item is the bigger then the other items he make the increasing sequence be longer
     *
     * return the length the long increasing sequence of an array
     * complexity = O(n * log(n))
     * @return
     */
    // increasing
    public static int dynamicLIS_size(int[] seq) { // O(n*log(n))
        int n = seq.length;
        int[] lis = new int[n];
        lis[0] = seq[0];
        int k = 1;
        for(int i = 0; i < n; i++) {
            int index = Arrays.binarySearch(lis, 0, k, seq[i]);
            if(index < 0) index = - index - 1; // fix java results
            if(index == k) k++; // make the increasing sequence be longer
            lis[index] = seq[i];
        }
        return k;
    }

    // decreasing
    public static int dynamicLDS_size(int[] seq) { // O(n*log(n))
        int n = seq.length;
        int[] lis = new int[n];
        lis[0] = seq[0];
        int k = 1;
        for(int i = n - 1; i > 0; i--) {
            int index = Arrays.binarySearch(lis, 0, k, seq[i]);
            if(index < 0) index = - index - 1; // fix java results
            if(index == k) k++; // make the increasing sequence be longer
            lis[index] = seq[i];
        }
        return k;
    }

    /**
     * return the LIS of an array
     * complexity = O(n^2)
     * @param seq
     * @return
     */
    public static int[] dynamicLIS(int[] seq) {
        int n = seq.length;
        int[] lis = new int[n];
        int[][] mat = new int[n][n];
        lis[0] = mat[0][0] = seq[0];
        int k = 1;
        for(int i = 0; i < n; i++) {
            int index = Arrays.binarySearch(lis, 0, k, seq[i]);
            if(index < 0) index = - index - 1; // fix java results
            if(index == k) k++; // make the increasing sequence be longer
            lis[index] = mat[index][index] = seq[i];
            for(int j = 0; j < index; j++) {
                mat[index][j] = mat[index - 1][j];
            }
        }
        return Arrays.copyOf(mat[k-1], k);
    }

    public static void main(String[] args) {
        int seq[] = {5,2,9,7,8,5,3,2,1,10,6};
        System.out.println(dynamicLIS_size(seq));
        System.out.println(dynamicLDS_size(seq));
        System.out.println(Arrays.toString(dynamicLIS(seq)));
    }
}
