import java.util.*;

public class BFSGraph {
    public static List<Integer> bfs(List<List<Integer>> adj, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            result.add(u);
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
        return result;
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
        System.out.println(bfs(adj, 2));
    }
}