
// 409_KosarajuAlgorithm.java
import java.util.*;

public class KosarajuAlgorithm {
  public static List<List<Integer>> kosaraju(int n, List<Integer>[] adj) {
    boolean[] visited = new boolean[n];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < n; i++)
      if (!visited[i])
        dfs1(adj, i, visited, stack);
    List<Integer>[] rev = new ArrayList[n];
    for (int i = 0; i < n; i++)
      rev[i] = new ArrayList<>();
    for (int u = 0; u < n; u++)
      for (int v : adj[u])
        rev[v].add(u);
    Arrays.fill(visited, false);
    List<List<Integer>> scc = new ArrayList<>();
    while (!stack.isEmpty()) {
      int v = stack.pop();
      if (!visited[v]) {
        List<Integer> comp = new ArrayList<>();
        dfs2(rev, v, visited, comp);
        scc.add(comp);
      }
    }
    return scc;
  }

  static void dfs1(List<Integer>[] adj, int v, boolean[] visited, Stack<Integer> stack) {
    visited[v] = true;
    for (int u : adj[v])
      if (!visited[u])
        dfs1(adj, u, visited, stack);
    stack.push(v);
  }

  static void dfs2(List<Integer>[] adj, int v, boolean[] visited, List<Integer> comp) {
    visited[v] = true;
    comp.add(v);
    for (int u : adj[v])
      if (!visited[u])
        dfs2(adj, u, visited, comp);
  }
}