import java.util.*;

public class CycleDetectionBFS {
    public static boolean hasCycle(List<List<Integer>> adj) {
        int V = adj.size();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (bfsCycleCheck(adj, i, visited))
                    return true;
            }
        }
        return false;
    }

    private static boolean bfsCycleCheck(List<List<Integer>> adj, int src, boolean[] visited) {
        int[] parent = new int[adj.size()];
        Arrays.fill(parent, -1);
        Queue<Integer> q = new LinkedList<>();
        visited[src] = true;
        q.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    q.add(v);
                } else if (parent[u] != v)
                    return true;
            }
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