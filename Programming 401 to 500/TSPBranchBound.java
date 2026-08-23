// 491_TSPBranchBound.java
import java.util.*;

public class TSPBranchBound {
    static int minCost = Integer.MAX_VALUE;
    public static int tspBranchBound(int[][] graph) {
        minCost = Integer.MAX_VALUE;
        int n = graph.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        dfs(graph, 0, visited, 0, 1, 0);
        return minCost;
    }
    static void dfs(int[][] graph, int pos, boolean[] visited, int count, int cost, int lowerBound) {
        if (count == graph.length) {
            if (graph[pos][0] > 0) minCost = Math.min(minCost, cost + graph[pos][0]);
            return;
        }
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i] && graph[pos][i] > 0) {
                int newCost = cost + graph[pos][i];
                // simple bound: cost + minimum outgoing
                if (newCost + minimalOutgoing(graph, visited, i) < minCost) {
                    visited[i] = true;
                    dfs(graph, i, visited, count + 1, newCost, lowerBound);
                    visited[i] = false;
                }
            }
        }
    }
    static int minimalOutgoing(int[][] graph, boolean[] visited, int node) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < graph.length; i++) if (!visited[i] && graph[node][i] > 0) min = Math.min(min, graph[node][i]);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}