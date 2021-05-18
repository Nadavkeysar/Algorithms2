
package practice_dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {

    private static final int inf = 1000000;

    public static void dijkstraAlgorithm(ArrayList<ArrayList<Integer>> G, int s, int t, int[][] weightMatrix) {
        int n = G.size();

        int[] pred = new int[n];
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            pred[i] = -1;
            dist[i] = inf;
            visited[i] = false;
        }

        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(s);
        dist[s] = 0;

        while(!q.isEmpty()) {
            int u = q.poll();
            for(int v : G.get(u)) {
                if(visited[v] == false && (dist[v] > dist[u] + weightMatrix[u][v])) {
                    q.add(v);
                    dist[v] = dist[u] + weightMatrix[u][v];
                    pred[v] = u;
                }
            }
            visited[u] = true;
            if(u == t) {
                break;
            }
        }

        // get shortest path
        int k = t;
        String path = Integer.toString(k);
        while(pred[k] != -1) {
//            System.out.println(path);
            k = pred[k];
            path = k + "->" + path;
        }

        System.out.println(path);
    }

    public static void main(String[] args) {
        int[][] mat = {{0 ,1 ,2 ,inf ,inf ,inf ,inf ,inf },
                {1 ,0 ,4 ,inf ,5 ,inf ,inf ,inf },
                {2 ,4 ,0 ,7 ,inf ,3 ,inf ,inf },
                {inf ,inf ,7 ,0 ,3 ,8 ,inf ,inf },
                {inf ,5 ,inf ,3 ,0 ,inf ,1 ,4 },
                {inf ,inf ,3 ,8 ,inf ,0 ,2 ,inf },
                {inf ,inf ,inf ,inf ,1 ,2 ,0 ,5 },
                {inf ,inf ,inf ,inf ,4 ,inf ,5 ,0 }};
        ArrayList<ArrayList<Integer>> G = InitializeGraph();
        dijkstraAlgorithm(G, 0, 7, mat);
    }

    public static ArrayList<ArrayList<Integer>> InitializeGraph() {
        ArrayList<ArrayList<Integer>> G = new ArrayList<ArrayList<Integer>>();
        int numOfVertices = 8;

        for (int i = 0; i < numOfVertices; i++) {
            G.add(new ArrayList<Integer>());
        }

        G.get(0).add(1);
        G.get(0).add(2);

        G.get(1).add(0);
        G.get(1).add(2);
        G.get(1).add(4);

        G.get(2).add(0);
        G.get(2).add(1);
        G.get(2).add(3);
        G.get(2).add(5);

        G.get(3).add(2);
        G.get(3).add(4);
        G.get(3).add(5);

        G.get(4).add(1);
        G.get(4).add(3);
        G.get(4).add(7);

        G.get(5).add(2);
        G.get(5).add(3);
        G.get(5).add(6);

        G.get(6).add(4);
        G.get(6).add(5);
        G.get(6).add(7);

        G.get(7).add(4);
        G.get(7).add(6);

        return G;
    }
}
