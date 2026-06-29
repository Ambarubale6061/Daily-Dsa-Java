import java.util.*;

public class DFSGraph {
    public static List<Integer> dfs(List<List<Integer>> adj, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        dfsHelper(adj, start, visited, result);
        return result;
    }

    private static void dfsHelper(List<List<Integer>> adj, int u, boolean[] visited, List<Integer> res) {
        visited[u] = true;
        res.add(u);
        for (int v : adj.get(u)) {
            if (!visited[v])
                dfsHelper(adj, v, visited, res);
        }
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(2);
        adj.get(2).add(0);
        adj.get(2).add(3);
        adj.get(3).add(3);
        System.out.println(dfs(adj, 2));
    }
}