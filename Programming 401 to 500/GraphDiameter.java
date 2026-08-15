// 471_GraphDiameter.java
public class GraphDiameter {
  public static int diameter(int n, List<Integer>[] adj) {
    int[] first = bfs(adj, 0);
    int[] second = bfs(adj, first[1]);
    return second[0];
  }

  static int[] bfs(List<Integer>[] adj, int start) {
    int n = adj.length;
    int[] dist = new int[n];
    Arrays.fill(dist, -1);
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    dist[start] = 0;
    int maxDist = 0, farthest = start;
    while (!q.isEmpty()) {
      int u = q.poll();
      for (int v : adj[u]) {
        if (dist[v] == -1) {
          dist[v] = dist[u] + 1;
          q.add(v);
          if (dist[v] > maxDist) {
            maxDist = dist[v];
            farthest = v;
          }
        }
      }
    }
    return new int[] { maxDist, farthest };
  }
}