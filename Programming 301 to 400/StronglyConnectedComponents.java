import java.util.*;

public class StronglyConnectedComponents {
    public static List<List<Integer>> kosaraju(int V, List<Integer>[] adj) {
        // step1: order by finish time
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            if (!visited[i])
                fillOrder(adj, i, visited, stack);
        // reverse graph
        List<Integer>[] rev = new ArrayList[V];
        for (int i = 0; i < V; i++)
            rev[i] = new ArrayList<>();
        for (int u = 0; u < V; u++)
            for (int v : adj[u])
                rev[v].add(u);
        // dfs on reversed graph
        Arrays.fill(visited, false);
        List<List<Integer>> scc = new ArrayList<>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> comp = new ArrayList<>();
                dfs(rev, v, visited, comp);
                scc.add(comp);
            }
        }
        return scc;
    }

    private static void fillOrder(List<Integer>[] adj, int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int u : adj[v])
            if (!visited[u])
                fillOrder(adj, u, visited, stack);
        stack.push(v);
    }

    private static void dfs(List<Integer>[] adj, int v, boolean[] visited, List<Integer> comp) {
        visited[v] = true;
        comp.add(v);
        for (int u : adj[v])
            if (!visited[u])
                dfs(adj, u, visited, comp);
    }

    public static void main(String[] args) {
        int V = 5;
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();
        adj[1].add(0);
        adj[0].add(2);
        adj[2].add(1);
        adj[0].add(3);
        adj[3].add(4);
        System.out.println(kosaraju(V, adj));
    }
}