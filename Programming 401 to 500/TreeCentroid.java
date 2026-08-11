
// 427_TreeCentroid.java
import java.util.*;

public class TreeCentroid {
  public static List<Integer> findCentroids(int n, List<Integer>[] adj) {
    int[] sz = new int[n];
    List<Integer> centroids = new ArrayList<>();
    dfs(0, -1, adj, sz, centroids, n);
    return centroids;
  }

  static void dfs(int u, int p, List<Integer>[] adj, int[] sz, List<Integer> centroids, int n) {
    sz[u] = 1;
    boolean isCentroid = true;
    for (int v : adj[u]) {
      if (v != p) {
        dfs(v, u, adj, sz, centroids, n);
        sz[u] += sz[v];
        if (sz[v] > n / 2)
          isCentroid = false;
      }
    }
    if (n - sz[u] > n / 2)
      isCentroid = false;
    if (isCentroid)
      centroids.add(u);
  }
}