
// 450_VertexDisjointPaths.java
import java.util.*;

public class VertexDisjointPaths {
  // Split vertices to capacity 1, edges capacity INF, max flow
  public static int maxVertexDisjointPaths(int n, List<Integer>[] adj, int s, int t) {
    int V = 2 * n;
    DinicMaxFlow dinic = new DinicMaxFlow(V);
    for (int u = 0; u < n; u++) {
      if (u == s || u == t)
        dinic.addEdge(u, u + n, Integer.MAX_VALUE);
      else
        dinic.addEdge(u, u + n, 1);
    }
    for (int u = 0; u < n; u++)
      for (int v : adj[u])
        dinic.addEdge(u + n, v, Integer.MAX_VALUE);
    return dinic.maxFlow(s, t + n);
  }
}