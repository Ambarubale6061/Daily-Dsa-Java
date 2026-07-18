import java.util.*;

public class BridgesInGraph {
    static int time = 0;

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (List<Integer> conn : connections) {
            adj[conn.get(0)].add(conn.get(1));
            adj[conn.get(1)].add(conn.get(0));
        }
        int[] disc = new int[n], low = new int[n];
        Arrays.fill(disc, -1);
        List<List<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, disc, low, adj, bridges);
        return bridges;
    }

    private static void dfs(int u, int parent, int[] disc, int[] low, List<Integer>[] adj,
            List<List<Integer>> bridges) {
        disc[u] = low[u] = ++time;
        for (int v : adj[u]) {
            if (v == parent)
                continue;
            if (disc[v] == -1) {
                dfs(v, u, disc, low, adj, bridges);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u])
                    bridges.add(Arrays.asList(u, v));
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> conn = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2), Arrays.asList(2, 0),
                Arrays.asList(1, 3));
        System.out.println(criticalConnections(4, conn)); // [[1,3]]
    }
}