package algorithms_1.maraton2;

import java.util.Arrays;
import java.util.HashMap;

public class LargestPower {

    // O(n)
    public static int longestSequenceWithConditionInNTimeSize(int[] arr) {
        HashMap<Integer, Integer> lsc = new HashMap<>();
        int n = arr.length, ans = 0;
        for(int i = 0; i < n; i++) {
            Integer x = lsc.get(arr[i] * arr[i]);
            Integer y = lsc.get((int)Math.sqrt(arr[i]));
            int max = 1;
            if(x != null) max = Math.max(max, x + 1);
            if(y != null && Math.sqrt(arr[i]) == (int)Math.sqrt(arr[i])) max = Math.max(max, y + 1);
            lsc.put(arr[i], max);
            if(max > ans) ans = max;
        }
        return ans;
    }

    /**
     * using dynamic programming
     * define f(i) = the length of the longest sequence s.t  i is the last index in the sequence
     * f(i) = MAX_i<j (f(i) + 1 if *, 1 else)
     * when * is mean:
     * a[i]^2 = a[j] or a[j]^2 = a[i] (condition)
     * @param arr
     * @return
     */

    // O(n^2)
    public static int longestSequenceWithConditionSize(int[] arr) {
        int n = arr.length;
        int[] lsc = new int[n];
        int ans = 1;
        for(int i = 0; i < n; i++) {
            int max = 0;
            for(int j = 0; i < i; j++) {
                if(condition(arr[i], arr[j])) {
                    if(lsc[j] + 1 > max) max = lsc[j] + 1;
                }
                lsc[i] = max;
                if(max > ans) ans = max;
            }
        }
        return ans;
    }

    // O(n^2)
    public static int[] longestSequenceWithCondition(int[] arr) {
        int n = arr.length;
        int[] lsc = new int[n];
        int ans = 1;
        int index = -1;
        for(int i = 0; i < n; i++) {
            int max = 0;
            for(int j = 0; i < i; j++) {
                if(condition(arr[i], arr[j])) {
                    if(lsc[j] + 1 > max) max = lsc[j] + 1;
                }
                lsc[i] = max;
                if(max > ans) {
                    ans = max;
                    index = i;
                }
            }
        }

        int[] seq = new int[ans];
        int k = ans - 1;
        while(ans > 1) {
            for(int i = 0; i < index; i++) {
                if(condition(arr[index], arr[i]) && lsc[i] == lsc[index] - 1) {
                    seq[k--] = arr[index];
                    index = i;
                    ans--;
                    break;
                }
            }
        }
        seq[0] = arr[index];
        return seq;
    }

    private static boolean condition(int x, int y) {
        return x*x == y || y*y == x;
    }

    private static boolean conditionLIS(int x, int y) {
        return x >= y;
    }

    private static boolean conditionABS(int x, int y) {
        return Math.abs(x - y) <= 1;
    }

    public static void main(String[] args) {
        int[] arr = {2,7,1,49,3,4,1,9,2,30,81};
        System.out.println(longestSequenceWithConditionSize(arr));
        System.out.println(longestSequenceWithConditionInNTimeSize(arr));
        System.out.println(Arrays.toString(longestSequenceWithCondition(arr)));
    }

}
