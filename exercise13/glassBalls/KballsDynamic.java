package algorithms_1.exercise13.glassBalls;
/**
 * 
 * Glass Balls Algorithm:
 * Get minimum number of checking 
 * to find the floor from which the bulls are broken
 * Using k balls and dividing the building by Dynamic programming:
 * min( max(f(n-i,b), f(i - 1,b - 1)) + 1)
 */
public class KballsDynamic {
	
	static int drops(int n, int k){
		int drops[][] = new int[n+1][k+1];
		for(int j = 0; j <= k; j++) { //one ball
			drops[0][j]=0;
			drops[1][j]=j;
		}
		for(int i = 2; i <= n; i++) { //i - number of the ball
			drops[i][0]=0;
			drops[i][1]=0;
			if (k >= 2) drops[i][2] = 2;
			for(int j = 2; j <= k; j++) { //j - number of the floor
					//Defines the minimum as the highest possible value
					int minimum = k+1;
					for(int x = 1; x < j; x++) { // x - number of the floor
						minimum=Math.min(minimum, (1 + Math.max(drops[i][j - x], drops[i - 1][x - 1])));
					}
					//Defines the minimum value for numdrops[i][j]
					drops[i][j]=minimum;			
			}
		}
		printMatrix(drops);
		return drops[n][k];
	}

	//**************************************************

	public static void printMatrix(int mat[][]){
		System.out.println("\nAttempt's number matrix\n");
		for (int i = 0; i < mat.length; i++){
			for (int j = 0; j < mat[0].length; j++){
				System.out.print( mat[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args){
		int b = 2;		//Number of balls
		int f = 100;	//Number of floors

		System.out.println("Ball dropping puzzle\n\nNumber of balls:" + b);
		System.out.println("Number of floors:" + f);
		long start = System.nanoTime();
		System.out.println("Number of drops in the worst case:  " 
				+ drops(b,f));
		long finish = System.nanoTime();
		System.out.println("time = " + (finish - start) + "  nanosec\n");
		System.out.println("******************************************");

		//*****************************************************************

		b = 3;		//Number of balls
		f = 100;	//Number of floors
		System.out.println("ball dropping puzzle\n\nNumber of balls:" + b);
		System.out.println("Number of floors:" + f);
		start = System.nanoTime();
		System.out.println("Number of drops in the worst case:  " 
				+ drops(b,f));
		finish = System.nanoTime();
		System.out.println("time = " + (finish - start) + "  nanosec\n");
	}

}

