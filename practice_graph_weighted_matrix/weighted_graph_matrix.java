package practice_graph_weighted_matrix;

public interface weighted_graph_matrix {
    public int[][] copy();
    public void connect(int i, int j, int w);
    public int getWeight(int i, int j);
    public void disconnect(int i, int j);
    public boolean isConnected(int i, int j);
    public void floyd_warshall();
    public String[][] path_matrix();
    public int size();
    public void clear();
}
