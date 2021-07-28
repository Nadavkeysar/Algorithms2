package algorithms_1.exercise10;

public class DonutsProblem {
	private static final int time = 2;
	
	/**
	 * Algorithm for solving the problem of donuts.
	 * The frying time of each side of the donut is one minute. 
	 * Complexity: O(1)
	 * @param numOfDonuts a number of donuts for a frying 
	 * @param capacity a number of donuts that go into the pan
	 * @return the total time in minutes for a frying of all daunts
	 */
	public static int getTime(int numOfDonuts,int capacity) {
		if(capacity >= numOfDonuts) return time;
		if((time*numOfDonuts)%capacity == 0) return (time*numOfDonuts)/capacity;
		return (time*numOfDonuts)/capacity + 1;
	}
	public static void main(String[] args) {
		System.out.println("#donuts = 12, pan capacity = 6, frying time = " + getTime(12,6));
		System.out.println("#donuts = 9, pan capacity = 6, frying time = " +getTime(9,6));
	}
}
