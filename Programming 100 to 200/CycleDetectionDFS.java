import java.util.*;

public class CycleDetectionDFS {
    public static boolean hasCycle(List<List<Integer>> adj) {
        int V = adj.size();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i] && dfsCheck(adj, i, -1, visited))
                return true;
        }
        return false;
    }

    private static boolean dfsCheck(List<List<Integer>> adj, int u, int parent, boolean[] visited) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                if (dfsCheck(adj, v, u, visited))
                    return true;
            } else if (v != parent)
                return true;
        }
        return false;
    }

    // test
    public static void main(String[] args) {
        int V = 3;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(0);
        adj.get(0).add(2);
        System.out.println(hasCycle(adj)); // true
    }
}