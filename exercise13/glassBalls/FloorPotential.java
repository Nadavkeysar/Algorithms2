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
import java.util.Random;

public class FloorPotential {
	
	private int numBreaks, numChecks, numFloor;
	private int floorPotential[], ballPotential;
	
	public FloorPotential(int[] x, int a){
		floorPotential = x;
		ballPotential = a;
		numBreaks  = numChecks = numFloor = 0;
		checkFloor();
	}
	
	public int numberOfBreaks(){
		return numBreaks;
	}
	public int numberOfChecking(){
		return numChecks;
	}
	public int floorIndex(){
		return numFloor;
	}
	
	private void checkFloor(){
		int n = floorPotential.length;
		boolean flag = true;
		int x = nearestTriangularNumber(n);
		int floor = x-1;
		// the first ball
		while (flag){ 
			numChecks++;
			if (floorPotential[floor] > ballPotential){ //the ball is broken
				numBreaks++;
				numFloor = floor;
				flag = false;
			}
			else{
				x = x - 1;
				if (floor + x < n-1) floor = floor + x;
				else{
					floor = n - 1 ;
					numChecks++;
					if (floorPotential[floor] > ballPotential){
						numFloor = floor;
						numBreaks++;
						flag = false;
					}
					else{
						numFloor = -1;
						flag = false;
					}
				}
			}
		}
		// the second ball
		if (numBreaks == 1){
			floor = floor - x + 1;
			while (numBreaks==1){
				numChecks++;
				if (floorPotential[floor] > ballPotential){
					numBreaks++;
					numFloor = floor;
				}
				else{
					floor = floor + 1;
				}
			}
		}
	}
	
	private int nearestTriangularNumber(int n){
		int x=0;
		double s = 1. + 8.*n;
		double k = Math.sqrt(s);
		int c = (int)k;
		if (c*c==s){
			x = (int)(-1 + k)/2;
			//System.out.println("triangular n = "+n + ", x = "+x);
		}
		else{
			int ceiling = (int)k + 1;
			if ((-1+ceiling)%2 == 1){
				x = (int)(-1 + ceiling)/2+1;
			}
			else x = (int)(-1 + ceiling)/2;
			//System.out.println("nearest triangular t = "+ x*(x+1)/2 + ", x = "+x);
		}
		return x;
	}
	
	public static int[] randArray(int n, int to){
		int a[] = new int[n];
		Random gen = new Random(17);
		for (int i = 0; i < a.length; i++) {
			a[i] = gen.nextInt(to);
		}
		return a;
	}
	
	public static void print(int[]a){
		for (int i = 0; i < a.length; i++) {
			if (i%25 == 0) System.out.println();
			System.out.print(a[i] + ", ");
		}
		System.out.println();
	}
	
	public static void test1(){
		System.out.println("test1");
		int [] floorPotential={1,2,3,4,5,6,7,8,9,10};
		int a = 7;
		System.out.println("For building of " + floorPotential.length + " floors, ball potential is " + a + ":");
		FloorPotential gb = new FloorPotential(floorPotential, a);
		System.out.println("number of breaks = "+gb.numberOfBreaks());
		System.out.println("number of checks = "+gb.numberOfChecking());
		System.out.println("floor index = "+gb.floorIndex());
		System.out.println();
	}
	public static void test2(){
		System.out.println("\ntest2");
		int [] floorPotential={1,2,3,4,5,6,7,8,9,10};
		int a = 10;
		System.out.println("For building of " + floorPotential.length + " floors, ball potential is " + a + ":");
		FloorPotential gb = new FloorPotential(floorPotential, a);
		System.out.println("number of breaks = "+gb.numberOfBreaks());
		System.out.println("number of checks = "+gb.numberOfChecking());
		System.out.println("floor index = "+gb.floorIndex());
		System.out.println();
	}
	public static void test3(){
		System.out.println("\ntest3");
		int [] floorPotential={3,5,8,14,15,18,27,32,35,40,44};
		int a = 12;
		System.out.println("For building of " + floorPotential.length + " floors, ball potential is " + a + ":");
		FloorPotential gb = new FloorPotential(floorPotential, a);
		System.out.println("number of breaks = "+gb.numberOfBreaks());
		System.out.println("number of checks = "+gb.numberOfChecking());
		System.out.println("floor index = "+gb.floorIndex());
		System.out.println();
	}

	public static void test4(){
		System.out.println("\ntest4");
		int [] floorPotential={3,5,8,14,15,18,27,32,35,40,44};
		int a =2;
		System.out.println("For building of " + floorPotential.length + " floors, ball potential is " + a + ":");
		FloorPotential gb = new FloorPotential(floorPotential, a);
		System.out.println("number of breaks = "+gb.numberOfBreaks());
		System.out.println("number of checks = "+gb.numberOfChecking());
		System.out.println("floor index = "+gb.floorIndex());
		System.out.println();
	}
	public static void test5(){
		System.out.println("\ntest5");
		int [] floorPotential={3,5,8,14,15,18,27,32,35,40,44};
		int a = 45;
		System.out.println("For building of " + floorPotential.length + " floors, ball potential is " + a + ":");
		FloorPotential gb = new FloorPotential(floorPotential, a);
		System.out.println("number of breaks = "+gb.numberOfBreaks());
		System.out.println("number of checks = "+gb.numberOfChecking());
		System.out.println("floor index = "+gb.floorIndex());
		System.out.println();
	}
	public static void test6(){
		int size = 100, a=49;
		int [] floorPotential = new int[size];
		for(int i=0; i<size; i++){
			floorPotential[i] = i;
		}
		System.out.println("For building of " + floorPotential.length + " floors, ball potential is " + a + ":");
		FloorPotential gb = new FloorPotential(floorPotential, a);
		System.out.println("number of breaks = "+gb.numberOfBreaks());
		System.out.println("number of checks = "+gb.numberOfChecking());
		System.out.println("floor index = "+gb.floorIndex());
		System.out.println();
	}
	public static void main(String[] args) {
	    test1();
		test2();
		test3();
		test4();
		test5();		
		test6();
	}	
}
/* 
test1
number of breaks = 2
number of checks = 4
floor index = 7


test2
number of breaks = 0
number of checks = 4
floor index = -1


test3
number of breaks = 2
number of checks = 5
floor index = 3


test4
number of breaks = 2
number of checks = 2
floor index = 0


test5
number of breaks = 0
number of checks = 3
floor index = -1

number of breaks = 2
number of checks = 6
floor index = 50


*/
