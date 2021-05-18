package exercise3;

public class Chocolate {

    public static int formula(int n, int k) {
        return n * (n - k);
    }

    public static void chocolate(int[] A, int N) {

    int[] B = new int[N];

    // Distribute 1 chocolate to each
        for (int i = 0; i < N; i++) {
        B[i] = 1;
    }

    // Traverse from left to right
        for (int i = 1; i < N; i++) {
        if (A[i] > A[i - 1])
            B[i] = B[i - 1] + 1;
        else
            B[i] = 1;
    }

    // Traverse from right to left
        for (int i = N - 2; i >= 0; i--) {
        if (A[i] > A[i + 1])
            B[i] = Math.max(B[i + 1] + 1, B[i]);
        else
            B[i] = Math.max(B[i], 1);
    }

    // Initialize sum
    int sum = 0;

    // Find total sum
        for (int i = 0; i < N; i++) {
        sum += B[i];
    }

    // Return sum
        System.out.print(sum + "\n");
}

    // Driver Code
    public static void main(String[] args)
    {

        // Given array
        int A[] = { 23, 14, 15, 14, 56, 29, 14 };

        // Size of the given array
        int N = A.length;
        chocolate(A, N);
    }

}
