
// 457_RandomizedDFS.java
import java.util.*;

public class RandomizedDFS {
  public static void dfs(List<Integer>[] adj, int u, boolean[] visited) {
    visited[u] = true;
    List<Integer> neighbors = new ArrayList<>(adj[u]);
    Collections.shuffle(neighbors);
    for (int v : neighbors)
      if (!visited[v])
        dfs(adj, v, visited);
  }
}