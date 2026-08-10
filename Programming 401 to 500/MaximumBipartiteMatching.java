
// 423_MaximumBipartiteMatching.java
import java.util.*;

public class MaximumBipartiteMatching {
  public static int hopcroftKarp(int n, int m, List<Integer>[] adj) {
    int[] pairU = new int[n], pairV = new int[m], dist = new int[n];
    Arrays.fill(pairU, -1);
    Arrays.fill(pairV, -1);
    int result = 0;
    while (bfs(adj, pairU, pairV, dist, n, m)) {
      for (int u = 0; u < n; u++)
        if (pairU[u] == -1 && dfs(adj, u, pairU, pairV, dist))
          result++;
    }
    return result;
  }

  static boolean bfs(List<Integer>[] adj, int[] pairU, int[] pairV, int[] dist, int n, int m) {
    Queue<Integer> q = new LinkedList<>();
    for (int u = 0; u < n; u++) {
      if (pairU[u] == -1) {
        dist[u] = 0;
        q.add(u);
      } else
        dist[u] = Integer.MAX_VALUE;
    }
    int INF = Integer.MAX_VALUE;
    boolean found = false;
    while (!q.isEmpty()) {
      int u = q.poll();
      for (int v : adj[u]) {
        int pu = pairV[v];
        if (pu != -1 && dist[pu] == INF) {
          dist[pu] = dist[u] + 1;
          q.add(pu);
        } else if (pu == -1)
          found = true;
      }
    }
    return found;
  }

  static boolean dfs(List<Integer>[] adj, int u, int[] pairU, int[] pairV, int[] dist) {
    for (int v : adj[u]) {
      int pu = pairV[v];
      if (pu == -1 || (dist[pu] == dist[u] + 1 && dfs(adj, pu, pairU, pairV, dist))) {
        pairU[u] = v;
        pairV[v] = u;
        return true;
      }
    }
    dist[u] = Integer.MAX_VALUE;
    return false;
  }
}