import java.util.*;

public class HamiltonianPath {
  static boolean dfs(int[][] graph, int v, int visited, int n, int count, List<Integer> path) {
    path.add(v);
    if (count == n)
      return true;
    for (int i = 0; i < n; i++) {
      if (graph[v][i] == 1 && (visited & (1 << i)) == 0) {
        if (dfs(graph, i, visited | (1 << i), n, count + 1, path))
          return true;
      }
    }
    path.remove(path.size() - 1);
    return false;
  }

  public static List<Integer> findHamiltonianPath(int[][] graph) {
    int n = graph.length;
    for (int i = 0; i < n; i++) {
      List<Integer> path = new ArrayList<>();
      if (dfs(graph, i, 1 << i, n, 1, path))
        return path;
    }
    return null;
  }
}