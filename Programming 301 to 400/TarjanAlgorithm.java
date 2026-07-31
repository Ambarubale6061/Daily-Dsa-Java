import java.util.*;

public class TarjanAlgorithm {
    // Tarjan's strongly connected components
    public static List<List<Integer>> tarjanSCC(int n, List<Integer>[] graph) {
        int[] disc = new int[n], low = new int[n];
        boolean[] inStack = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> scc = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (disc[i] == 0)
                dfs(i, graph, disc, low, inStack, stack, scc, new int[] { 0 });
        return scc;
    }

    private static void dfs(int u, List<Integer>[] graph, int[] disc, int[] low, boolean[] inStack,
            Stack<Integer> stack, List<List<Integer>> scc, int[] time) {
        disc[u] = low[u] = ++time[0];
        stack.push(u);
        inStack[u] = true;
        for (int v : graph[u]) {
            if (disc[v] == 0) {
                dfs(v, graph, disc, low, inStack, stack, scc, time);
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v])
                low[u] = Math.min(low[u], disc[v]);
        }
        if (low[u] == disc[u]) {
            List<Integer> comp = new ArrayList<>();
            while (true) {
                int v = stack.pop();
                inStack[v] = false;
                comp.add(v);
                if (v == u)
                    break;
            }
            scc.add(comp);
        }
    }
}