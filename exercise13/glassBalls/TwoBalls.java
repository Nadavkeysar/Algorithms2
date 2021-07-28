package algorithms_1.exercise13.glassBalls;
/**
 * 
 * Glass Balls Algorithm:
 * Get minimum number of checking 
 * to find the floor from which the balls are broken
 * Using 2 balls and dividing the building by Dynamic programming
 */
public class TwoBalls {
	//f(n,2) = min((max(i, f(n-i,2)+1), i=1,...,n) where n is a number of flour 
	public static int numberOfChecking2(int n) {
		int numChecks = 0;
		int[] f = new int[n+1];
		if (n == 1) numChecks = 1;
		else if (n == 2) numChecks = 2;
		else { //n>=3
			f[0] = 0; f[1] = 1; f[2] = 2;
			for (int i=3; i<=n; i++) {
				int min = n;
				for(int j = 1; j < i; j++){
					int x = Math.max(j, f[i-j]+1);
					if (x < min) min = x;
				}
				f[i] = min;
			}
			numChecks = f[n];
		}
		return numChecks;
	}

	public static void check2(){
		int[] tringleNumbers = {3,6,10,15,21,28,36,45,55,66,78,91,105,120,
				136,153,171,190,210,231,253,276,300,325,351,378,406};
		System.out.println("For number of floors as triangle numbers:");
		for (int i=0; i<tringleNumbers.length; i++){
			System.out.print("#floors: " + tringleNumbers[i]);
			System.out.println(" -> #drops: " +numberOfChecking2(tringleNumbers[i])+"; ");
		}
		System.out.println("\n\nFor number of floors that are from 1 to 100:");
		for (int i=1; i<=100; i++){
			if (i%20 == 0) System.out.println();
			System.out.print("#floors: " + i);
			System.out.print(" -> #drops: " + numberOfChecking2(i)+"; ");
		}
	}
	public static void main(String[] args) {
		System.out.println("number of balls = 2");
		System.out.println(numberOfChecking2(100));
	}
}
/*
	number of balls = 2
	dividing by triangle numbers
	2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 
	
	from 1 to 100
	1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 
	6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 
	9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 
	11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 
	13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 
	14, 
*/
