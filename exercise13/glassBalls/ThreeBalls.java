package algorithms_1.exercise13.glassBalls;
/**
 * 
 * Glass Balls Algorithm:
 * Get minimum number of checking 
 * to find the floor from which the bulls are broken
 * Using 3 balls and dividing the building by Dynamic programming
 */
public class ThreeBalls {
	public static int numberOfChecking3(int n){

		int numChecks = 0;
		int[] f3 = new int[n+1];
		if (n==1) numChecks = 1;
		else if (n==2 || n==3) numChecks = 2;
		else{ //n>=3
			int[] f2 = new int[n+1];
			for (int i=1; i<=n; i++){
				f2[i] = TwoBalls.numberOfChecking2(i);
			}

			f3[0] = 0; f3[1] = 1; f3[2] = 2; f3[3] = 2;
			for (int i=4; i<=n; i++){
				int min = n;
				for (int j = 1; j<i; j++){
					int x = Math.max(f2[j-1], f3[i-j]) + 1;
					if (x < min) min = x;
				}
				f3[i] = min;
			}
			numChecks = f3[n];
		}
		return numChecks;
	}
	public static void check3(){
		int[] tringleNumbers = {3,6,10,15,21,28,36,45,55,66,78,91,105,120,
				136,153,171,190,210,231,253,276,300,325,351,378,406};
		System.out.println("For number of floors as triangle numbers:");
		for (int i=0; i<tringleNumbers.length; i++){
			System.out.print("#floors: " + tringleNumbers[i]);
			System.out.println(" -> #drops: " +numberOfChecking3(tringleNumbers[i])+"; ");
		}
		System.out.println("\n\nFor number of floors that are from 1 to 100:");
		for (int i=1; i<=100; i++){
			if (i%20 == 0) System.out.println();
			System.out.print("#floors: " + i);
			System.out.print(" -> #drops: " + numberOfChecking3(i)+"; ");
		}
	}
	public static void main(String[] args) {
		System.out.println("\nnumber of balls = 3");
		System.out.println(numberOfChecking3(100));
	}
}
