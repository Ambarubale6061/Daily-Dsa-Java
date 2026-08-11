
// 449_EdgeDisjointPaths.java
import java.util.*;

public class EdgeDisjointPaths {
  // Max flow gives edge-disjoint paths count
  public static int maxEdgeDisjointPaths(int n, List<Integer>[] adj, int s, int t) {
    DinicMaxFlow dinic = new DinicMaxFlow(n);
    for (int u = 0; u < n; u++)
      for (int v : adj[u])
        dinic.addEdge(u, v, 1);
    return dinic.maxFlow(s, t);
  }
}