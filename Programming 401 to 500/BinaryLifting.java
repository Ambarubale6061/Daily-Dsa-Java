
// 430_BinaryLifting.java
import java.util.*;

public class BinaryLifting {
  int[][] up;
  int[] depth;
  int LOG;

  public BinaryLifting(List<Integer>[] adj, int n) {
    LOG = (int) (Math.log(n) / Math.log(2)) + 1;
    up = new int[n][LOG];
    depth = new int[n];
    dfs(0, -1, adj);
  }

  void dfs(int u, int p, List<Integer>[] adj) {
    up[u][0] = p == -1 ? 0 : p;
    for (int i = 1; i < LOG; i++)
      up[u][i] = up[up[u][i - 1]][i - 1];
    for (int v : adj[u])
      if (v != p) {
        depth[v] = depth[u] + 1;
        dfs(v, u, adj);
      }
  }

  public int lca(int u, int v) {
    if (depth[u] < depth[v]) {
      int t = u;
      u = v;
      v = t;
    }
    int diff = depth[u] - depth[v];
    for (int i = 0; i < LOG; i++)
      if ((diff & (1 << i)) != 0)
        u = up[u][i];
    if (u == v)
      return u;
    for (int i = LOG - 1; i >= 0; i--)
      if (up[u][i] != up[v][i]) {
        u = up[u][i];
        v = up[v][i];
      }
    return up[u][0];
  }
}