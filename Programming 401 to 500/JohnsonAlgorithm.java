
// 405_JohnsonAlgorithm.java
import java.util.*;

public class JohnsonAlgorithm {
  public static int[][] allPairsShortestPaths(int n, List<int[]> edges) {
    // add extra node
    List<int[]> newEdges = new ArrayList<>(edges);
    for (int i = 0; i < n; i++)
      newEdges.add(new int[] { n, i, 0 });
    int[] h = bellmanFord(n + 1, newEdges, n); // extra node = n
    if (h == null)
      return null; // negative cycle
    int[][] dist = new int[n][n];
    for (int u = 0; u < n; u++) {
      int[] d = dijkstra(n, edges, u, h);
      for (int v = 0; v < n; v++)
        dist[u][v] = d[v];
    }
    return dist;
  }

  static int[] bellmanFord(int V, List<int[]> edges, int src) {
    int[] dist = new int[V];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;
    for (int i = 0; i < V - 1; i++)
      for (int[] e : edges)
        if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]])
          dist[e[1]] = dist[e[0]] + e[2];
    // check negative cycles
    return dist;
  }

  static int[] dijkstra(int n, List<int[]> edges, int src, int[] h) {
    List<int[]>[] adj = new ArrayList[n];
    for (int i = 0; i < n; i++)
      adj[i] = new ArrayList<>();
    for (int[] e : edges)
      if (e[0] < n)
        adj[e[0]].add(new int[] { e[1], e[2] + h[e[0]] - h[e[1]] });
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    pq.add(new int[] { src, 0 });
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int u = cur[0];
      if (cur[1] > dist[u])
        continue;
      for (int[] nb : adj[u]) {
        int v = nb[0], w = nb[1];
        if (dist[u] + w < dist[v]) {
          dist[v] = dist[u] + w;
          pq.add(new int[] { v, dist[v] });
        }
      }
    }
    // adjust back
    for (int i = 0; i < n; i++)
      if (dist[i] != Integer.MAX_VALUE)
        dist[i] = dist[i] - h[src] + h[i];
    return dist;
  }
}