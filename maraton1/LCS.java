package algorithms_1.maraton1;

// LCS = Longest Common Subsequence
public class LCS {

    /**
     * complexity: O(n*m), n = s1.length, m = s2.length
     *
     * dynamic function:
     * f(i,j) = LCS(s1, s2).length when s1 is limited to index i
     *                              and s2 is limited to index j
     * 0 <= i <= n, 0 <= j <= m
     *
     * recursive definition of the dynamic function
     * f(0,j) = f(i,0) = 0
     * f(i,j) = 1 + f(i-1, j-1) if s1[i] == s2[j]
     * f(i,j) = MAX(f(i, j-1), f(i-1, j)) if s1[i] != s2[j]
     *
     * @param s1
     * @param s2
     * @return LCS size between s1 and s2
     */
    public static int dynamicLCS_size(String s1, String s2) {

        // s1 and s2 lengths
        int n = s1.length(), m = s2.length();

        // build the matrix
        int[][] f = new int[n+1][m+1];

        // left column and top row are zeros
        for(int i = 0; i < n+1; i++) f[i][0] = 0;
        for(int j = 0; j < m+1; j++) f[0][j] = 0;

        // build the rest of the matrix
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < m+1; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) f[i][j] = 1 + f[i-1][j-1];
                else f[i][j] = Math.max(f[i-1][j], f[i][j-1]);
            }
        }

        // size of the LCS
        return f[n][m];
    }

    /**
     * O(n*m) + O(n+m) = O(nm)
     * @param s1
     * @param s2
     * @return LCS between s1 and s2
     */
    public static String dynamicLCS(String s1, String s2) {

        // s1 and s2 lengths
        int n = s1.length(), m = s2.length();

        // build the matrix
        int[][] f = new int[n+1][m+1];

        // left column and top row are zeros
        for(int i = 0; i < n+1; i++) f[i][0] = 0;
        for(int j = 0; j < m+1; j++) f[0][j] = 0;

        // build the rest of the matrix
        for(int i = 1; i < n+1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) f[i][j] = 1 + f[i - 1][j - 1];
                else f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
            }
        }

        // f[n][m] is the size of the LCS

        // build back the LCS
        int i = n, j = m;
        String ans = "";

        // going backward on the matrix - O(n+m)
        while(f[i][j] != 0) {
            if(s1.charAt(i-1) == s2.charAt(j-1)) {
                ans = s1.charAt(i-1) + ans;
                i--;
                j--;
            } else {
                if(f[i][j-1] > f[i-1][j]) j--;
                else i--;
            }
        }
        return ans;
    }

    /**
     * base case:
     * f(0,j,k) = f(i,0,k) = f(i,j,0) = 0
     * f(i,j,k) = 1 + f(i-1,j-1,k-1), if s1[i] == s2[j] == s3[k]
     *
     * f(i,j,k) = max(f(i-1,j,k), f(i,j-1,k), f(i,j,k-1))
     *
     * complexity = O(n*m*l)
     * n = s1.length
     * m = s2.length
     * l = s3.length
     * @param s1
     * @param s2
     * @param s3
     */
    public static void dynamicLCS3(String s1, String s2, String s3) {

    }

    /**
     * return the smallest common sequence (strings) length between two strings
     * for example:
     * s1 = "abcbdab" = 7
     * s2 = "bdcaba" = 6
     * LCS(s1, s2) = 4 ("bcba")
     * SCS(s1, s2) = 9 ("abdcabdab")
     * solution: |SCS(s1, s2)| = |s1| + |s2| - |LCS(s1, s2)|
     * @param s1
     * @param s2
     * @return
     */
    public static int SCS_size(String s1, String s2) {
        return s1.length() + s2.length() - dynamicLCS_size(s1, s2);
    }

    /**
     * return the smallest common sequence (strings) between two strings
     * with the condition: s1 is in ans and s2 is in ans
     * for example:
     * s1 = "abcbdab"
     * s2 = "bdcaba"
     * SCS(s1, s2) = ("abdcabdab")
     * @param s1
     * @param s2
     * @return ans - the SCS
     */
    static String dynamicSCS(String s1, String s2) {

        // s1 and s2 length
        int m = s1.length(), n = s2.length();

        // build matrix
        int dp[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        Common.printMatrix(dp);

        // build string
        String ans = "";
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if(s1.charAt(i - 1) == s2.charAt(j - 1)) { ans += (s1.charAt(i - 1)); i--; j--; }
            else if(dp[i - 1][j] > dp[i][j - 1]) { ans += (s2.charAt(j - 1)); j--; }
            else { ans += (s1.charAt(i - 1)); i--; }
        }

        while(i > 0) { ans += (s1.charAt(i - 1)); i--; }
        while(j > 0) { ans += (s2.charAt(j - 1)); j--; }



        // reversing the array
        char[] tempArray = ans.toCharArray();
        int left, right = 0;
        right = tempArray.length - 1;
        for(left = 0; left < right; left++, right--) {
            char temp = tempArray[left];
            tempArray[left] = tempArray[right];
            tempArray[right] = temp;
        }
        ans = String.valueOf(tempArray);
        return ans;
    }

    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        //  String s1 = "abcde";
        //  String s2 = "eafddbac";
        //  System.out.println(dynamicLCS(s1, s2)); // output: "abc"
        //  System.out.println(dynamicLCS_size(s1, s2)); // output: 3

        String s3 = "abcbdab";
        String s4 = "bdcaba";
        System.out.println(dynamicLCS_size(s3, s4));

    }
}
