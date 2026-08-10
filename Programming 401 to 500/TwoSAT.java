
// 422_TwoSAT.java
import java.util.*;

public class TwoSAT {
  int n;
  List<Integer>[] adj, radj;
  boolean[] visited, assign;
  Stack<Integer> stack;

  public TwoSAT(int n) {
    this.n = n;
    adj = new ArrayList[2 * n];
    radj = new ArrayList[2 * n];
    for (int i = 0; i < 2 * n; i++) {
      adj[i] = new ArrayList<>();
      radj[i] = new ArrayList<>();
    }
  }

  void addImpl(int u, int v) {
    adj[u].add(v);
    radj[v].add(u);
  }

  public void addClause(int x, boolean xVal, int y, boolean yVal) {
    addImpl(2 * x + (xVal ? 0 : 1), 2 * y + (yVal ? 1 : 0));
    addImpl(2 * y + (yVal ? 0 : 1), 2 * x + (xVal ? 1 : 0));
  }

  void dfs1(int v) {
    visited[v] = true;
    for (int u : adj[v])
      if (!visited[u])
        dfs1(u);
    stack.push(v);
  }

  void dfs2(int v, int comp) {
    visited[v] = true;
    assign[v] = comp == 0;
    for (int u : radj[v])
      if (!visited[u])
        dfs2(u, comp);
  }

  public boolean solve() {
    visited = new boolean[2 * n];
    stack = new Stack<>();
    for (int i = 0; i < 2 * n; i++)
      if (!visited[i])
        dfs1(i);
    Arrays.fill(visited, false);
    assign = new boolean[2 * n];
    while (!stack.isEmpty()) {
      int v = stack.pop();
      if (!visited[v])
        dfs2(v, 0);
    }
    for (int i = 0; i < n; i++)
      if (assign[2 * i] == assign[2 * i + 1])
        return false;
    return true;
  }
}