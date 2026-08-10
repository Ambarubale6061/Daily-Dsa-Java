
// 435_DSUOnTree.java
import java.util.*;

public class DSUOnTree {
  // "Sack" (dsu on tree) for answering subtree queries (e.g., count distinct
  // colors)
  static int[] colors, cnt, res;
  static List<Integer>[] adj;
  static int currentMax;

  public static void solve(int n, int[] col, int[][] queries) {
    colors = col;
    cnt = new int[n + 1];
    res = new int[n];
    // not full query implementation; concept
  }

  void dfs(int u, int p, boolean keep) {
    for (int v : adj[u])
      if (v != p && v != heavyChild(u))
        dfs(v, u, false);
    if (heavyChild(u) != -1)
      dfs(heavyChild(u), u, true);
    for (int v : adj[u])
      if (v != p && v != heavyChild(u))
        addSubtree(v);
    add(u);
    // answer queries for u
    if (!keep)
      removeSubtree(u);
  }

  int heavyChild(int u) {
    return -1;
  } // placeholder

  void add(int v) {
  }

  void addSubtree(int v) {
  }

  void removeSubtree(int v) {
  }
}