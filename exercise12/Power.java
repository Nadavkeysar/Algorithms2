package algorithms_1.exercise12;

public class Power {
	
	/**
	 * Calculate power by iteration
	 * @param x basis
	 * @param n exponent
	 * Complexity: O(log n)
	 * @return x^n by iteration
	 */
	public static double powerIterative(double x, int n) {
		double ans = 1;
		while(n != 0) {
			if(n % 2 == 1) ans *= x;
			x = x*x;
			n /=2;
		}
		return ans;
	}
	
	/**
	 * Calculate power by recursive
	 * @param x basis
	 * @param n exponent
	 * Complexity: O(log n)
	 * @return x^n by recursive
	 */
	public static double powerRecursive(double x, int n) {
		if(n == 0) return 1;
		if(n % 2 == 1) return powerRecursive(x*x,(n-1)/2)*x;
		return powerRecursive(x*x,n/2);
	}

	public static double powUsingBinary(double x, int n) {
		double ans = 1;
		while(n != 0) {
			ans *= ((n & 1) != 0) ? x : 1;
			x *= x;
			n >>= 1;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		//System.out.println("5^4 = " + powerIterative(5.0, 4) + "\n");
		
		System.out.println("***** Power Iterative O(log n) *****");
		long start = System.nanoTime();
		for (int i = 0; i <100000; i++)
			powerIterative(5.0, 10000);
		long finish = System.nanoTime();
		System.out.println("time of 5^10000 = " + (finish - start) + " nanosec");
		
		start = System.nanoTime();
		for (int i = 0; i <100000; i++)
			powerIterative(2.7, 14000);
		finish = System.nanoTime();
		System.out.println("time of 2.7^14000 = " + (finish - start) + " nanosec");
		
		start = System.nanoTime();
		for (int i = 0; i <100000; i++)
			powerIterative(3.58, 9000);
		finish = System.nanoTime();
		System.out.println("time of 3.58^9000 = " + (finish - start) + " nanosec");
		
		////////////////////////////////////////////////////////////
		//System.out.println("\n5^4 = " + powerRecursive(5.0, 4) + "\n");
		System.out.println("***** Power Recursive O(log n) *****");
		start = System.nanoTime();
		for (int i = 0; i <10000000; i++)
			powerRecursive(5.0,100);
		finish = System.nanoTime();
		System.out.println("time of 5^100 = " + (finish - start) + " nanosec");
		
		start = System.nanoTime();
		for (int i = 0; i <10000000; i++)
			powerRecursive(2.7, 140);
		finish = System.nanoTime();
		System.out.println("time of 2.7^140 = " + (finish - start) + " nanosec");
		
		start = System.nanoTime();
		for (int i = 0; i <10000000; i++)
			powerRecursive(3.58, 90);
		finish = System.nanoTime();
		System.out.println("time of 3.58^90 = " + (finish - start) + " nanosec");
	}
}
