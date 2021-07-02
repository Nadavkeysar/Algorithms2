package algorithms_2.assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Diameter {
    // Used to track farthest node.
    static int x;
    static int maxCount;
    boolean[][] adj_matrix;
    int n;

    public Diameter(boolean[][] adj_matrix) {
        this.adj_matrix = adj_matrix;
        this.n = adj_matrix.length;
    }

    private int[][] matrix_bool_to_int(boolean[][] mat) {
        int[][] l = new int[mat.length][mat[0].length];
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++) {
                if(mat[i][j]) {
                    l[i][j] = 1;
                } else {
                    l[i][j] = 0;
                }
            }
        }
        return l;
    }

    // Sets maxCount as maximum distance
    // from node
    void dfsUtil(int node, int count, boolean visited[]) {
        visited[node] = true;
        count++;

        int[][] adj = matrix_bool_to_int(adj_matrix);

        int[] l = adj[node];
        for(Integer i: l)
        {
            if(!visited[i]){
                if (count >= maxCount) {
                    maxCount = count;
                    x = i;
                }
                dfsUtil(i, count, visited);
            }
        }
    }

    // The function to do DFS traversal. It uses
    // recursive dfsUtil()
    void dfs(int node, int n)
    {
        boolean[] visited = new boolean[n + 1];
        int count = 0;

        // Mark all the vertices as not visited
        Arrays.fill(visited, false);

        // Increment count by 1 for visited node
        dfsUtil(node, count + 1, visited);

    }

    // Returns diameter of binary tree represented
    // as adjacency list.
    public int get_diam() {
        maxCount = Integer.MIN_VALUE;

        /* DFS from a random node and then see
        farthest node X from it*/
        dfs(1, n);

        /* DFS from X and check the farthest node
        from it */
        dfs(x, n);

        return maxCount+1;
    }

    /* Driver program to test above functions*/
    public static void main(String args[])
    {
        boolean[][] adj = {
                { false, true, false, false, false, false},
                { true, false, true, true, false, false},
                { false, true, false, false, false, false},
                { false, true, false, false, true, true},
                { false, false, false, true, false, false},
                { false, false, false, true, false, false}
        };
        Diameter di = new Diameter(adj);
        System.out.println(di.get_diam());
    }
}
