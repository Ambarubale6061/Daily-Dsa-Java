
// 418_DominatorTree.java
import java.util.*;

public class DominatorTree {
  // Simple O(n^2) algorithm for dominator tree
  public static int[] dominators(int n, List<Integer>[] adj, int src) {
    int[] dom = new int[n];
    for (int i = 0; i < n; i++)
      dom[i] = (i == src) ? src : -1;
    boolean[] reachable = new boolean[n];
    dfs(adj, src, reachable);
    boolean changed = true;
    while (changed) {
      changed = false;
      for (int u = 0; u < n; u++) {
        if (u == src || !reachable[u])
          continue;
        int newIdom = -1;
        for (int p : reverseAdj(adj)[u]) {
          if (dom[p] != -1) {
            if (newIdom == -1)
              newIdom = p;
            else
              newIdom = intersect(dom, newIdom, p);
          }
        }
        if (newIdom != -1 && dom[u] != newIdom) {
          dom[u] = newIdom;
          changed = true;
        }
      }
    }
    return dom;
  }

  static void dfs(List<Integer>[] adj, int v, boolean[] vis) {
    vis[v] = true;
    for (int u : adj[v])
      if (!vis[u])
        dfs(adj, u, vis);
  }

  static int intersect(int[] dom, int a, int b) {
    while (a != b) {
      while (a > b)
        a = dom[a];
      while (b > a)
        b = dom[b];
    }
    return a;
  }

  static List<Integer>[] reverseAdj(List<Integer>[] adj) {
    int n = adj.length;
    List<Integer>[] rev = new ArrayList[n];
    for (int i = 0; i < n; i++)
      rev[i] = new ArrayList<>();
    for (int u = 0; u < n; u++)
      for (int v : adj[u])
        rev[v].add(u);
    return rev;
  }
}