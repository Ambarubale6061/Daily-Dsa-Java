
// 467_ZeroOneBFS.java
import java.util.*;

public class ZeroOneBFS {
  public static int shortestPath(int n, List<int[]>[] adj, int s, int t) {
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    Deque<Integer> dq = new LinkedList<>();
    dist[s] = 0;
    dq.addFirst(s);
    while (!dq.isEmpty()) {
      int u = dq.pollFirst();
      for (int[] edge : adj[u]) {
        int v = edge[0], w = edge[1];
        if (dist[u] + w < dist[v]) {
          dist[v] = dist[u] + w;
          if (w == 0)
            dq.addFirst(v);
          else
            dq.addLast(v);
        }
      }
    }
    return dist[t];
  }
}