
// 434_RerootingDP.java
import java.util.*;

public class RerootingDP {
  // Compute sum of distances to all nodes for each node in a tree
  static long[] sumDist;
  static int[] subSize;

  public static long[] sumOfDistances(int n, List<Integer>[] adj) {
    sumDist = new long[n];
    subSize = new int[n];
    dfs1(0, -1, adj);
    dfs2(0, -1, adj, n);
    return sumDist;
  }

  static void dfs1(int u, int p, List<Integer>[] adj) {
    subSize[u] = 1;
    for (int v : adj[u]) {
      if (v != p) {
        dfs1(v, u, adj);
        subSize[u] += subSize[v];
        sumDist[0] += subSize[v];
      }
    }
  }

  static void dfs2(int u, int p, List<Integer>[] adj, int n) {
    for (int v : adj[u]) {
      if (v != p) {
        sumDist[v] = sumDist[u] - subSize[v] + (n - subSize[v]);
        dfs2(v, u, adj, n);
      }
    }
  }
}