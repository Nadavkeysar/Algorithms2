package algorithms_1.exercise11;

import java.util.ArrayList;

/**
 * class AeroplaneProblem presents
 * how find one best/cheapest path, all best/cheapest paths
 * and more creative questions
 */
public class AeroplaneProblem {
	int numOfPaths, cheapestPrice;
	private Node [][]mat;
	
	public AeroplaneProblem(Node[][] nodes){
		numOfPaths = 0;
		cheapestPrice = 0;
		mat = nodes;
	}
	
	/**
	 * Build a matrix that contains the price to get each point (from (0,0)) 
	 * and the number of shortest path until each point
	 * Complexity: O(n*m)
	 */
	public void getBestPrice(){
		// n rows, m columns
		int n = mat.length, m = mat[0].length;
		mat[0][0].price = 0;
		for (int i=1; i<n; i++){// first column
			mat[i][0].price = mat[i-1][0].y+  mat[i-1][0].price;
			mat[i][0].numOfPaths = 1;
		}
		for (int j=1; j<m; j++){// first row
			mat[0][j].price = mat[0][j-1].price +  mat[0][j-1].x;
			mat[0][j].numOfPaths = 1;
		}
		for (int i=1; i<n; i++){
			for (int j=1; j<m; j++){
				int a = mat[i-1][j].price+mat[i-1][j].y;
				int b = mat[i][j-1].price+mat[i][j-1].x;
				if (a<b){
					mat[i][j].price = a;
					mat[i][j].numOfPaths = mat[i-1][j].numOfPaths;				
				}
				else if (a>b) {
					mat[i][j].price = b;
					mat[i][j].numOfPaths = mat[i][j-1].numOfPaths;
				}
				else{//x==y
					mat[i][j].price = a;
					mat[i][j].numOfPaths = mat[i][j-1].numOfPaths+mat[i-1][j].numOfPaths;
				}
			}
		}
		numOfPaths = mat[n-1][m-1].numOfPaths;
		cheapestPrice = mat[n-1][m-1].price;
	}
	
	public int getNumOfPaths(){return numOfPaths;}
	public int getCheapestPrice(){return cheapestPrice;}
	
	//******One Cheapest Path*********
	/**
	 * Calculate One Best Path by Induction
	 * Complexity: O(n+m) - but first need to build the matrix - O(n*m)
	 * @return one of shortest path - induction
	 */	
	String getOneCheapestPath(){
		String ans = "";
		int i = mat.length-1, j = mat[0].length-1;
		while(i>0 && j>0){
			int a = mat[i-1][j].price+mat[i-1][j].y;
			int b = mat[i][j-1].price+mat[i][j-1].x;
			if (a < b){
				ans = "1" + ans;
				i--;
			}
			else{//a>b
				ans = "0" + ans;
				j--;
			}
		}
		if (i==0){
			while (j>0){
				ans = "0" + ans;
				j--;
			}
		}
		else {//j==0
			while (i>0){
				ans = "1" + ans;
				i--;
			}
		}
		return ans;
	}
	
	//******All Cheapest Paths*********
	/**
	 * Calculate all cheapest paths by recursion
	 * Complexity: O((m+n)*numOfPaths)
	 * @param teta
	 */
	public  void AllPathsRecurs(int teta){
		if (numOfPaths<=teta){
			ArrayList<String> paths = new  ArrayList<String>(numOfPaths);
			buildPaths(new String(), mat.length-1, mat[0].length-1, paths);
			System.out.println(paths);
		}
	}
	public void buildPaths(String path, int i, int j, ArrayList<String> paths){
		if (i==0 && j==0){
			paths.add(path);
		}
		else if (i==0 && j > 0){
			/*String t = new String();
			for(int k=0; k<j; k++) t = t +  "0";
			paths.add(t + path);*/
			buildPaths("0"+path, i, j-1, paths);
		}
		else if (j==0 && i > 0){
			/*String t = new String();
			for(int k=0; k<i; k++) t = t +  "1";
			paths.add(t + path);*/
			buildPaths("1"+path, i-1, j, paths);
		}
		else{
			int a = mat[i-1][j].price+mat[i-1][j].y;
			int b = mat[i][j-1].price+mat[i][j-1].x;
			if (a < b){
				buildPaths("1"+path, i-1, j, paths);
			}
			else if(a > b){
				buildPaths("0"+path, i, j-1, paths);
			}
			else{//a==b
				buildPaths("1"+path, i-1, j, paths);
				buildPaths("0"+path, i, j-1, paths);
			}
		}
	}
	
	/******Creative questions*********	
	/**
	 * This function calculates the minimal price from point (p1,q1) to (p2,q2):
	 * the minimal price in submatrix from bottom left point (p1, q1) to upper right point (p2 ,q2)  
	 * @return the minimal price from point (p1,q1) to (p2,q2)
	 * Complexity: O(n*m)
	 */
	public int getBestPrice(int p1, int q1, int p2, int q2){
		mat[p1][q1].price = 0;
		for (int i=p1+1; i<=p2; i++){// first column
			mat[i][q1].price = mat[i-1][q1].y+  mat[i-1][q1].price;
			mat[i][q1].numOfPaths = 1;
		}
		for (int j=q1+1; j<=q2; j++){// first row
			mat[p1][j].price = mat[p1][j-1].price +  mat[p1][j-1].x;
			mat[p1][j].numOfPaths = 1;
		}
		for (int i=p1+1; i<=p2; i++){
			for (int j=q1+1; j<=q2; j++){
				int a = mat[i-1][j].price+mat[i-1][j].y;
				int b = mat[i][j-1].price+mat[i][j-1].x;
				if (a<b){
					mat[i][j].price = a;
					mat[i][j].numOfPaths = mat[i-1][j].numOfPaths;				
				}
				else if (a>b) {
					mat[i][j].price = b;
					mat[i][j].numOfPaths = mat[i][j-1].numOfPaths;
				}
				else{//x==y
					mat[i][j].price = a;
					mat[i][j].numOfPaths = mat[i][j-1].numOfPaths+mat[i-1][j].numOfPaths;
				}
			}
		}
		//numOfPaths = mat[p2][q2].numOfPaths;
		return mat[p2][q2].price;
	}
	
	/**
	 * This function checks if the given point is on one of the shortest paths
	 * @return true if the given point is on one of the shortest paths
	 * Complexity: O(n*m) 	 
	 */
	public boolean isOnBestPath(int i,int j) {	
		return getCheapestPrice()==getBestPrice(0, 0, i, j)+getBestPrice(i, j, mat.length-1, mat[0].length-1);
	}

	/**
	 * This function finds the shortest path with minimal turnings 
	 * returns the shortest path with minimal turnings 
	 * Complexity: O((n+m)choose(n)*(m+n))
	 */
	public String optimalPath() {
		ArrayList<String> paths = getAllPathsRecurs();
		String ans = "";
		int min = Integer.MAX_VALUE;
		for(String path : paths) {
			int turning = 0;
			for (int i = 1; i < path.length(); i++) {
				if(path.charAt(i) != path.charAt(i-1)) turning++;
			}
			if(turning < min) {
				ans = path;
				min = turning;
			}
		}
		return ans;
	}
	/**
	 * 
	 * @return all shortest paths by recursion
	 */
	public ArrayList<String> getAllPathsRecurs(){		
			ArrayList<String> paths = new ArrayList<String>(numOfPaths);
			buildPaths("", mat.length-1, mat[0].length-1, paths);
			return paths;	
	}
	
	public static void main(String[] args) {
		int teta = 10;
		Node [][]nodes = InitMatrixOfPrices.initMatrix();
		AeroplaneProblem ap = new AeroplaneProblem(nodes);
		ap.getBestPrice();
		System.out.println("The price of the cheapest path: "+ap.getCheapestPrice());
		System.out.println("The number of the cheapest paths: "+ap.getNumOfPaths());
		
		System.out.println("\nOne cheapest path: " + ap.getOneCheapestPath());
		
		System.out.print ("\nAll cheapest paths: ");
		ap.AllPathsRecurs(teta);
		System.out.println("\nShortest path with minimal turnings: " + ap.optimalPath());

		System.out.println("\nIf a point at [1,1] is on the best path? " + ap.isOnBestPath(1,1));
		System.out.println("If a point at [0,1] is on the best path? " + ap.isOnBestPath(0,1));
		System.out.println("If a point at [3,0] is on the best path? " + ap.isOnBestPath(3,0));
 

	}

}
