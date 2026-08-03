
// 408_BidirectionalDijkstra.java
import java.util.*;

public class BidirectionalDijkstra {
  static class Edge {
    int to, w;

    Edge(int t, int w) {
      to = t;
      this.w = w;
    }
  }

  public static long biDijkstra(List<Edge>[] adj, int s, int t) {
    int n = adj.length;
    long[] dist1 = new long[n], dist2 = new long[n];
    Arrays.fill(dist1, Long.MAX_VALUE);
    Arrays.fill(dist2, Long.MAX_VALUE);
    dist1[s] = 0;
    dist2[t] = 0;
    PriorityQueue<long[]> pq1 = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
    PriorityQueue<long[]> pq2 = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
    pq1.add(new long[] { s, 0 });
    pq2.add(new long[] { t, 0 });
    boolean[] visited1 = new boolean[n], visited2 = new boolean[n];
    long best = Long.MAX_VALUE;
    while (!pq1.isEmpty() && !pq2.isEmpty()) {
      if (pq1.peek()[1] + pq2.peek()[1] >= best)
        break;
      // expand forward
      long[] cur = pq1.poll();
      int u = (int) cur[0];
      if (visited1[u])
        continue;
      visited1[u] = true;
      for (Edge e : adj[u]) {
        int v = e.to;
        long nd = dist1[u] + e.w;
        if (nd < dist1[v]) {
          dist1[v] = nd;
          pq1.add(new long[] { v, nd });
        }
        if (visited2[v] && dist1[u] + e.w + dist2[v] < best)
          best = dist1[u] + e.w + dist2[v];
      }
      // backward similar (omitted for brevity but should include)
      cur = pq2.poll();
      int v = (int) cur[0];
      if (visited2[v])
        continue;
      visited2[v] = true;
      for (Edge e : adj[v]) {
        int u2 = e.to;
        long nd = dist2[v] + e.w;
        if (nd < dist2[u2]) {
          dist2[u2] = nd;
          pq2.add(new long[] { u2, nd });
        }
        if (visited1[u2] && dist2[v] + e.w + dist1[u2] < best)
          best = dist2[v] + e.w + dist1[u2];
      }
    }
    return best == Long.MAX_VALUE ? -1 : best;
  }
}