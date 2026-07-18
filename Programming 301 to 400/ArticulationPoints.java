import java.util.*;

public class ArticulationPoints {
    static int time = 0;

    public static List<Integer> findArticulationPoints(int n, List<List<Integer>> connections) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (List<Integer> c : connections) {
            adj[c.get(0)].add(c.get(1));
            adj[c.get(1)].add(c.get(0));
        }
        int[] disc = new int[n], low = new int[n];
        boolean[] ap = new boolean[n];
        Arrays.fill(disc, -1);
        for (int i = 0; i < n; i++)
            if (disc[i] == -1)
                dfs(i, -1, disc, low, adj, ap);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (ap[i])
                res.add(i);
        return res;
    }

    private static void dfs(int u, int parent, int[] disc, int[] low, List<Integer>[] adj, boolean[] ap) {
        disc[u] = low[u] = ++time;
        int children = 0;
        for (int v : adj[u]) {
            if (v == parent)
                continue;
            if (disc[v] == -1) {
                children++;
                dfs(v, u, disc, low, adj, ap);
                low[u] = Math.min(low[u], low[v]);
                if (parent != -1 && low[v] >= disc[u])
                    ap[u] = true;
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (parent == -1 && children > 1)
            ap[u] = true;
    }

    public static void main(String[] args) {
        List<List<Integer>> conn = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2), Arrays.asList(2, 0),
                Arrays.asList(1, 3));
        System.out.println(findArticulationPoints(4, conn)); // [1]
    }
}