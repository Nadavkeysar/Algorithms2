package practice_graph_boolean_matrix;

public interface boolean_graph_matrix {
    public boolean[][] copy();
    public void connect(int i, int j);
    public void disconnect(int i, int j);
    public boolean isConnected(int i, int j);
    public void floyd_warshall();
    public String[][] path_matrix();
    public int size();
    public void clear();
}
