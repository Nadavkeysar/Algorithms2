package exercise1;

public class _01_Bottles_Problem {

    public static void main(String[] args) {
        int m = 1; // first bottle
        int n = 2; // second bottle
        boolean[][] mat = BottlesProblem(m,n);//first method
        Print(mat);
    }

    public static boolean[][] BottlesProblem(int m, int n) {
        int size = (m + 1) * (n + 1);
        boolean[][] mat = new boolean[size][size];

        // first method
/*		int k;
		for (int i = 0; i < m+1; i++) {
			for (int j = 0; j < n+1; j++) {
				k = getIndex(i,j,n);
				mat[k][getIndex(0,j,n)] = true;
				mat[k][getIndex(i,0,n)] = true;
				mat[k][getIndex(m,j,n)] = true;
				mat[k][getIndex(i,n,n)] = true;
				mat[k][getIndex(i+j-(Math.min(i+j, n)),Math.min(i+j, n),n)] = true;
				mat[k][getIndex(Math.min(i+j, m),i+j-Math.min(i+j, m),n)] = true;
			}
		}*/

        // second method
        int i,j;
        for (int k = 0; k < mat.length; k++) {
            i = getI(k,n);
            j = getJ(k,n);
            mat[k][getIndex(0,j,n)] = true;
            mat[k][getIndex(i,0,n)] = true;
            mat[k][getIndex(m,j,n)] = true;
            mat[k][getIndex(i,n,n)] = true;
            mat[k][getIndex(i+j-(Math.min(i+j, n)),Math.min(i+j, n),n)] = true;
            mat[k][getIndex(Math.min(i+j, m),i+j-Math.min(i+j, m),n)] = true;
        }
        return mat;
    }

    public static int getIndex(int i, int j, int n)
    {
        return (n+1)*i + j;
    }

    public static int getI(int k, int n)
    {
        return k / (n+1);
    }

    public static int getJ(int k, int n)
    {
        return k % (n+1);
    }

    public static void Print(boolean[][] mat)
    {
        int rows = mat.length;
        int cols = mat[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(mat[i][j]+"\t");
            }
            System.out.println();
        }
    }

}