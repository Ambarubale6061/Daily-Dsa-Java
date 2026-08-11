
// 426_TreeDiameter.java
import java.util.*;

public class TreeDiameter {
  public static int treeDiameter(int n, List<Integer>[] adj) {
    int[] farthest = bfs(adj, 0);
    farthest = bfs(adj, farthest[1]);
    return farthest[0];
  }

  static int[] bfs(List<Integer>[] adj, int start) {
    int n = adj.length;
    int[] dist = new int[n];
    Arrays.fill(dist, -1);
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    dist[start] = 0;
    int maxDist = 0, farthestNode = start;
    while (!q.isEmpty()) {
      int u = q.poll();
      for (int v : adj[u]) {
        if (dist[v] == -1) {
          dist[v] = dist[u] + 1;
          q.add(v);
          if (dist[v] > maxDist) {
            maxDist = dist[v];
            farthestNode = v;
          }
        }
      }
    }
    return new int[] { maxDist, farthestNode };
  }
}