package exercise6;

import java.util.ArrayList;

public class _05_Dijkstra {
    static int inf = 1000000;
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
        DijkstraAlgorithm(G, 0, 7, mat);

    }

    private static void DijkstraAlgorithm(ArrayList<ArrayList<Integer>> G, int s, int t, int[][] weightMatrix) {
        int V = G.size();
        int[] dist = new int[V];
        int[] prev = new int[V];
        boolean[] visited  = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = inf;
            prev[i] = -1;
            visited[i] = false;
        }
        dist[s] = 0;
        int v;
        while (((v = ExtractMin(dist, visited)) != -1))
        {
            for (int u : G.get(v)) {
                if (visited[u] == false && (dist[u] > dist[v] + weightMatrix[v][u]))
                {
                    dist[u] = dist[v] + weightMatrix[v][u];
                    prev[u] = v;
                }
            }
            visited[v] = true;
            if (v == t)
                break;
        }

        System.out.println("dist = " + dist[t]);
        int k = t;
        String path = "";
        while (prev[k] != -1)
        {
            path = "->"+ k + path;
            k = prev[k];
        }
        path = k  + path;
        System.out.println("path = " + path);

        System.out.println("==========");
        for (int i = 0; i < V; i++)
        {
            if (i==s)
                continue;
            PrintPath(prev, i);
            System.out.println();

        }

    }

    private static void PrintPath(int[] prev, int i) {
        if (prev[i] == -1) {
            System.out.print(i);
        }
        else {
            PrintPath(prev, prev[i]);
            System.out.print("-"+i);
        }
    }

    private static int ExtractMin(int[] dist, boolean[] visited) {
        int minIndex = Integer.MAX_VALUE, MinValue = Integer.MAX_VALUE;
        int V = visited.length;
        for (int i = 0; i < V; i++) {
            if (visited[i] == false && dist[i]<MinValue)
            {
                minIndex = i;
                MinValue = dist[i];
            }
        }
        return minIndex;
    }

    public static ArrayList<ArrayList<Integer>> InitializeGraph()
    {
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