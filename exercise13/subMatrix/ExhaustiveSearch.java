package algorithms_1.exercise13.subMatrix;

/**
 * Finding the largest submatrix that consists the ones only
 * by Exhaustive Search
 */
public class ExhaustiveSearch {

	public static void main(String[] args) {
		int mat[][] = {{0,0,0,1,0}, {1,1,1,1,0}, {0,1,1,1,1},
				{1,1,1,1,0},{1,1,0,0,1}};
		int mat1[][] = {{0,0,1,0},{0,0,1,1},{0,0,1,1},{1,1,1,1}};
		int mat2[][] = {{0,1,1},{1,1,0},{1,1,0}};
		exhaustiveSearch2(mat1);
	}
	
	/**
	 * Exhaustive Search
	 * Complexity: O(n^5)
	 * @param mat
	 */
	public static void exhaustiveSearch2(int[][]mat){
		int n = mat.length;
		int iStart = 0, jStart = 0, iEnd = 0, jEnd = 0;
		int maxDim = 0;
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++){
				if (mat[i][j]==1) {
					int k = 1, p=i+k, q=j+k;				
					while (p<n && q<n){
						if(checkForOne(mat,i,j,p,q) && k+1 > maxDim){
							iStart = i;  jStart = j;
							iEnd = p;  jEnd = q;
							maxDim = k+1;
						}
						k = k + 1;
						p = i + k; q = j + k;
					}
				}
			}	
		}
		if (iEnd==n) iEnd--;
		if (jEnd==n) jEnd--;
		
		System.out.println(" maxDim: "+maxDim+", iStart=" + iStart+", jStart="+jStart+", iEnd="+iEnd+", jEnd="+jEnd);
	}

	/**
	 * Checking if matrix consists the ones only
	 * Complexity: O(n^2)
	 * @param mat a submatrix
	 * @param iStart begin index in the row
	 * @param jStart begin index in the column
	 * @param iEnd end index in the row
	 * @param jEnd end index in the column
	 * @return true if matrix consists the ones only, another false
	 */
	public static boolean checkForOne(int [][]mat, int iStart, int jStart, int iEnd, int jEnd){
		boolean ans = true;
		for (int i=iStart; ans && i<=iEnd; i++){
			for (int j = jStart; ans && j<=jEnd; j++){
				if (mat[i][j] == 0) ans = false;
			}
		}
		return ans;
	}
}
