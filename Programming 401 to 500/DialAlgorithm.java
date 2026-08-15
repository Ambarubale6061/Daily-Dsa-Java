
// 468_DialAlgorithm.java
import java.util.*;

public class DialAlgorithm {
  // Dial's algorithm for small integer weights
  public static int[] dial(int n, List<int[]>[] adj, int src, int maxW) {
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;
    List<Deque<Integer>> buckets = new ArrayList<>();
    for (int i = 0; i <= maxW * n; i++)
      buckets.add(new LinkedList<>());
    buckets.get(0).add(src);
    for (int d = 0; d < buckets.size(); d++) {
      while (!buckets.get(d).isEmpty()) {
        int u = buckets.get(d).poll();
        if (d != dist[u])
          continue;
        for (int[] e : adj[u]) {
          int v = e[0], w = e[1];
          if (dist[u] + w < dist[v]) {
            dist[v] = dist[u] + w;
            buckets.get(dist[v]).add(v);
          }
        }
      }
    }
    return dist;
  }
}