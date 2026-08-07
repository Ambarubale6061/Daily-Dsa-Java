
// 413_HamiltonianCycle.java
import java.util.*;

public class HamiltonianCycle {
  static boolean dfs(int[][] graph, int v, int start, int visited, int count, int n, List<Integer> path) {
    path.add(v);
    if (count == n) {
      if (graph[v][start] == 1)
        return true;
      path.remove(path.size() - 1);
      return false;
    }
    for (int i = 0; i < n; i++) {
      if (graph[v][i] == 1 && (visited & (1 << i)) == 0) {
        if (dfs(graph, i, start, visited | (1 << i), count + 1, n, path))
          return true;
      }
    }
    path.remove(path.size() - 1);
    return false;
  }

  public static List<Integer> hamiltonianCycle(int[][] graph) {
    int n = graph.length;
    List<Integer> path = new ArrayList<>();
    if (dfs(graph, 0, 0, 1 << 0, 1, n, path))
      return path;
    return null;
  }
}