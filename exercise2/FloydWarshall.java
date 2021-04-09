package exercise2;

public class FloydWarshall {

    public static void floyd_warshall(boolean[][] T) {
        int size = T.length;
        for(int k = 0; k < size; k++) {
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    T[i][j] = (T[i][k] && T[k][j]) || T[i][k];
                }
            }
        }
    }

    public static void floyd_warshall(boolean[][] T, String[][] paths) {
        int size = T.length;
        for(int k = 0; k < size; k++) {
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if(!T[i][j] && (T[i][k] && T[k][j]))
                        paths[i][j] = paths[i][k] + "->" + paths[k][j];
                    T[i][j] = (T[i][k] && T[k][j]) || T[i][j];
                }
            }
        }
    }

//    private static void fixPath(String[][] path, int n) {
//        int size = path.length;
//        for(int i = 0; i < size; i++) {
//            for(int j = 0; j < size; j++) {
//                path[i][j] += "->("+
//            }
//        }
//    }

    /**
     * is connected function of boolean matrix
     * complexity: O(n^2)
     * @param mat
     * @return
     */
    public static boolean isConnected(boolean mat[][]) {
        boolean ans = true;
        for(int i = 0; ans && i < mat.length; i++) {
            for(int j = 0; ans && j < mat.length; j++) {
                if(!mat[i][j]) ans = false;
            }
        }
        return ans;
    }

    /**
     * is connected function of boolean matrix
     * complexity: O(n)
     * @param mat
     * @return
     */
    public static boolean isConnectedOn(boolean mat[][]) {
        boolean ans = true;
        for(int i = 0; ans && i < mat.length; i++) {
            if(mat[i][0]) ans = false;
        }
        return false;
    }



}
