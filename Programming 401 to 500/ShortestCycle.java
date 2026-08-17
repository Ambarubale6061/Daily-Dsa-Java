
// 469_ShortestCycle.java
import java.util.*;

public class ShortestCycle {
  public static int shortestCycle(int n, List<Integer>[] adj) {
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      int[] dist = new int[n], parent = new int[n];
      Arrays.fill(dist, -1);
      Queue<Integer> q = new LinkedList<>();
      q.add(i);
      dist[i] = 0;
      while (!q.isEmpty()) {
        int u = q.poll();
        for (int v : adj[u]) {
          if (dist[v] == -1) {
            dist[v] = dist[u] + 1;
            parent[v] = u;
            q.add(v);
          } else if (v != parent[u]) {
            ans = Math.min(ans, dist[u] + dist[v] + 1);
          }
        }
      }
    }
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }
}