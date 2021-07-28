package algorithms_1.exercise13.glassBalls;
/**
 * 
 * Glass Balls Algorithm:
 * Get minimum number of checking 
 * to find the floor from which the bulls are broken
 * Using 2 balls and dividing the building by triangular numbers
 * This dividing is better than division into equal parts
 * because sqrt(2*n)<=2*sqrt(n)
 * Complexity:  O(sqrt(2*n)) -> O(sqrt(n))
 */
public class GlassBallTriangle {
    // x is the k-th triangular number: triangular root of n is k-th triangular number:
	// x = (sqrt(8*n+1) - 1)/2  - from quadratic formula
	public static int nearestTriangularNumber(int n){
		int x=0;
		double s = 1. + 8.*n;
		double k = Math.sqrt(s);
		int c = (int)k;
		if (c*c==s){
			x = (-1 + c)/2;
		}
		else{
			x = (int)((-1+k)/2.) + 1;
		}
		//System.out.println("nearest triangular of "+ n + " is t = " + x*(x+1)/2 + ", that is k-th triangular number = "+x);
		return x;
	}
	
	// floorPotential[0]>0 - the potential of the first floor 
	// if (floorPotential[i] >=  ballPotential) then the ball is broken
	public static void glassBallsTriangle(int []floorPotential, int ballPotential){
		//n = the number of floors, t - the current floor
		int n = floorPotential.length;
		int k = nearestTriangularNumber(n);
		int step = k, numChecks = 0, t = k-1;
		boolean isBroken = false;
		if (floorPotential[n-1] < ballPotential){// the first ball is not broken
			System.out.println("the first ball is not broken");
			return;
		}
		// the first ball
		while(!isBroken && t < n){
			numChecks++;
			if (isCrashed(floorPotential[t], ballPotential)) isBroken = true;
			else t = t + (--step);
		}
		// the second ball
		if (t > n )t = t - k;
		else t = t - step + 1;
		isBroken = false;
		//  throw the second ball
		while(!isBroken){
			numChecks++;
			if (isCrashed(floorPotential[t], ballPotential)) isBroken = true;
			else t++;
		}
		System.out.println("k = "+k+", "+", numChecks = "+numChecks+", floor number from which the bulls are broken =  "+(t+1) +"\n");
	}
	
	public static boolean isCrashed(int floorPotential, int ballPotential){
		return floorPotential >= ballPotential;
	}
	
	public static void checkTriangularNumber(){
		System.out.println("for 100: " + nearestTriangularNumber(100));
		System.out.println("for 50: " + nearestTriangularNumber(50));
		System.out.println("for 28: " + nearestTriangularNumber(28));
		System.out.println("for 10: " + nearestTriangularNumber(10));
		System.out.println("for 3: " + nearestTriangularNumber(3));
	}
	
	public static void test1(){
		int size = 100, a=49;
		int [] floorPotential = new int[size];
		for(int i=0; i<size; i++){
			floorPotential[i] = i+1;
		}
		System.out.println("For building of " + size + " floors, ball potential is " + a + ":");
		glassBallsTriangle(floorPotential, a);
	}
	public static void test2(){
		int size = 10, a=9;
		int [] floorPotential = new int[size];
		for(int i=0; i<size; i++){
			floorPotential[i] = i+1;
		}
		System.out.println("For building of " + size + " floors, ball potential is " + a + ":");
		glassBallsTriangle(floorPotential, a);
	}
	public static void test3(){
		int size = 10, a=10;
		int [] floorPotential = new int[size];
		for(int i=0; i<size; i++){
			floorPotential[i] = i+1;
		}
		System.out.println("For building of " + size + " floors, ball potential is " + a + ":");
		glassBallsTriangle(floorPotential, a);
	}
	public static void test4(){
		//n=11
		int [] floorPotential={3,5,8,14,15,18,27,32,35,40,44};
		int a = 12;
		System.out.println("For building of " + floorPotential.length + " floors, ball potential is " + a + ":");
		glassBallsTriangle(floorPotential, a);
	}
	public static void test5(){
		//n=11
		int [] floorPotential={3,5,8,14,15,18,27,32,35,40,44};
		int a = 2;
		System.out.println("For building of " + floorPotential.length + " floors, ball potential is " + a + ":");
		glassBallsTriangle(floorPotential, a);
	}
	public static void test6(){
		//n=11
		int [] floorPotential={3,5,8,14,15,18,27,32,35,40,44};
		int a = 45;
		System.out.println("For building of " + floorPotential.length + " floors, ball potential is " + a + ":");
		glassBallsTriangle(floorPotential, a);
	}
	
	public static void main(String[] args) {
		//checkTriangularNumber();
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
	}
}
