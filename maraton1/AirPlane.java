package algorithms_1.maraton1;

import java.awt.*;

public class AirPlane {

    /**
     * f(i,j) = the minimum price for the path from (0,0) to (i,j)
     * result = f(n,m)
     *
     * define f function:
     *
     * base case:
     * f(0,0) = 0
     *
     * moving right:
     * f(0,i) = f(0,i-1) + M[0][i-1].x
     *
     * moving up:
     * f(i,0) = f(i-1,0) + M[i-1][0].y
     *
     * general case:
     * f(i,j) = MIN(f(i-1,j) + M[i-1][j].y, f(i,j-1) + M[i][j-1].x)
     *
     */

    /**
     * return the minimum price of the path
     * @param mat
     * @return
     */
    public static int minPrice(Node[][] mat) { // O(n * m)
        int n = mat.length, m = mat[0].length;
        mat[0][0].price = 0;

        for(int i = 1; i < n; i++) mat[i][0].price = mat[i-1][0].price + mat[i-1][0].y;
        for(int i = 1; i < m; i++) mat[0][i].price = mat[0][i-1].price + mat[0][i-1].x;
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                mat[i][j].price = Math.min(mat[i-1][j].price + mat[i-1][j].y, mat[i][j-1].price + mat[i][j-1].x);
            }
        }
        return mat[n-1][m-1].price;
    }


    public static int numberOfMinPrice(Node[][] mat) {
        int n = mat.length, m = mat[0].length;
        mat[0][0].price = 0;
        mat[0][0].numOfPath = 1;

        for(int i = 1; i < n; i++) { mat[i][0].price = mat[i-1][0].price + mat[i-1][0].y; mat[i][0].numOfPath = 1; }
        for(int i = 1; i < m; i++) { mat[0][i].price = mat[0][i-1].price + mat[0][i-1].x; mat[0][i].numOfPath = 1; }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                int fromDown = mat[i-1][j].price + mat[i-1][j].y;
                int fromLeft = mat[i][j-1].price + mat[i][j-1].x;
                mat[i][j].price = Math.min(fromDown, fromLeft);
                if(fromDown < fromLeft) mat[i][j].numOfPath = mat[i-1][j].numOfPath;
                else if(fromDown > fromLeft) mat[i][j].numOfPath = mat[i][j-1].numOfPath;
                else mat[i][j].numOfPath = mat[i-1][j].numOfPath + mat[i][j-1].numOfPath;
            }
        }
        return mat[n-1][m-1].numOfPath;
    }

    /**
     * return the minimum path
     * Complexity = O(n * m)
     * @param mat
     * @return
     */
    public static String minPricePath(Node[][] mat) {
        int n = mat.length, m = mat[0].length;
        mat[0][0].price = 0;

        for(int i = 1; i < n; i++) mat[i][0].price = mat[i-1][0].price + mat[i-1][0].y;
        for(int i = 1; i < m; i++) mat[0][i].price = mat[0][i-1].price + mat[0][i-1].x;
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                mat[i][j].price = Math.min(mat[i-1][j].price + mat[i-1][j].y, mat[i][j-1].price + mat[i][j-1].x);
            }
        }
        return path(mat);
    }

    /**
     * build minimum path from matrix
     * @param mat
     * @return
     */
    private static String path(Node[][] mat) { // O(n + m)
        int i = mat.length -1, j = mat[0].length - 1;
        String ans = "";
        while (i != 0 && j != 0) {
            if(mat[i-1][j].price + mat[i-1][j].y < mat[i][j-1].price + mat[i][j-1].x) { ans = "1" + ans;i--; }
            else { ans = "0" + ans; j--; }
        }

        while(i != 0) { ans = "1" + ans; i--; }
        while(j != 0) { ans = "0" + ans; j--; }
        return ans;
    }

    /**
     * return the minimum size of path between two points
     * Complexity = O(n * m)
     * @param mat
     * @param p1
     * @param p2
     * @return
     */
    public static int minPriceBetween(Node[][] mat, Point p1, Point p2) {
        int n = p2.y - p1.y + 1, m = p2.x - p1.x + 1;
        mat[p1.y][p1.x].price = 0;

        for(int i = p1.y + 1; i < p1.y + n; i++) mat[i][p1.x].price = mat[i-1][p1.x].price + mat[i-1][p1.x].y;
        for(int i = p1.x + 1; i < p1.x + m; i++) mat[p1.y][i].price = mat[p1.y][i-1].price + mat[p1.y][i-1].x;
        for(int i = p1.y + 1; i < p1.y + n; i++) {
            for(int j = p1.x + 1; j < p1.x + m; j++) {
                mat[i][j].price = Math.min(mat[i-1][j].price + mat[i-1][j].y, mat[i][j-1].price + mat[i][j-1].x);
            }
        }
        return mat[p2.y][p2.x].price;
    }

    /**
     * this function return true if at least one of the nodes in on one of the shortest path
     * between (0,0) and (n,m)
     *
     * solution:
     * first we compare between (p1,q1) and (p2,q2), when n1 = (p1,q1) and n2 = (p2,q2)
     * if p1 <= p2 and q1 <= q2: the first node in the path is (p1,q1)
     * if p2 <= p1 and q2 <= q1: the first node in the path is (p2,q2)
     *
     * if and only if:
     * minPricePathBetween((0,0),(m,n)) = minPricePathBetween((0,0),(p1,q1))
     *                                  + minPricePathBetween((p1,q1),(p2,q2))
     *                                  + minPricePathBetween((p2,q2),(m,n))
     *
     * Complexity = O(n * m)
     * @param mat
     * @param p1
     * @param p2
     * @return
     */
    public static boolean isOnMinPath(Node[][] mat, Point p1, Point p2) {
        if(p2.x <= p1.x && p2.y <= p1.y) {

            // swap
            Point t = p1;
            p1 = p2;
            p2 = t;
        }
        if(p1.x <= p2.x && p1.y <= p2.y) {
            int allPrice = minPriceBetween(mat, new Point(0,0), new Point(mat.length - 1, mat[0].length - 1));
            int toP1 = minPriceBetween(mat, new Point(0,0), p1);
            int p1toP2 = minPriceBetween(mat, p1, p2);
            int p2to = minPriceBetween(mat, p2, new Point(mat.length - 1, mat[0].length - 1));
            if(allPrice == toP1 + p1toP2 + p2to) return true;
            else return false;

        } else return false;
    }

    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        Node[][] mat = {
                { new Node(1, 5), new Node(4, 1), new Node(0, 6) },
                { new Node(4, 7), new Node(2, 5), new Node(0, 3) },
                { new Node(1, 0), new Node(2, 0), new Node(0, 0) }
            };
        System.out.println(minPrice(mat));
        System.out.println(minPricePath(mat));
        System.out.println(minPriceBetween(mat, new Point(0,0), new Point(2,2)));
        System.out.println(isOnMinPath(mat, new Point(1,1), new Point(1,2)));
    }
}
