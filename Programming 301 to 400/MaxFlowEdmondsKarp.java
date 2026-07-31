import java.util.*;

public class MaxFlowEdmondsKarp {
    public static int maxFlow(int n, int source, int sink, List<int[]>[] graph) {
        int[][] capacity = new int[n][n];
        for (int u = 0; u < n; u++) {
            for (int[] edge : graph[u]) {
                int v = edge[0], cap = edge[1];
                capacity[u][v] = cap;
            }
        }
        int[] parent = new int[n];
        int maxFlow = 0;
        while (bfs(source, sink, capacity, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v]);
            }
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= pathFlow;
                capacity[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    private static boolean bfs(int src, int sink, int[][] capacity, int[] parent) {
        Arrays.fill(parent, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        parent[src] = src;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < capacity.length; v++) {
                if (parent[v] == -1 && capacity[u][v] > 0) {
                    parent[v] = u;
                    if (v == sink)
                        return true;
                    q.add(v);
                }
            }
        }
        return false;
    }
}