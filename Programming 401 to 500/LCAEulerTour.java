
// 429_LCAEulerTour.java
import java.util.*;

public class LCAEulerTour {
  int[] first, depth, euler;
  SparseTable st;

  public LCAEulerTour(int n, List<Integer>[] adj, int root) {
    first = new int[n];
    depth = new int[n];
    List<Integer> eulerTour = new ArrayList<>();
    boolean[] visited = new boolean[n];
    dfs(root, -1, 0, adj, visited, eulerTour);
    int m = eulerTour.size();
    euler = eulerTour.stream().mapToInt(i -> i).toArray();
    int[] depths = new int[m];
    for (int i = 0; i < m; i++)
      depths[i] = depth[euler[i]];
    st = new SparseTable(depths);
  }

  void dfs(int u, int p, int d, List<Integer>[] adj, boolean[] visited, List<Integer> eulerTour) {
    visited[u] = true;
    depth[u] = d;
    if (first[u] == 0)
      first[u] = eulerTour.size();
    eulerTour.add(u);
    for (int v : adj[u]) {
      if (v != p) {
        dfs(v, u, d + 1, adj, visited, eulerTour);
        eulerTour.add(u);
      }
    }
  }

  public int lca(int u, int v) {
    int l = Math.min(first[u], first[v]), r = Math.max(first[u], first[v]);
    int idx = st.queryIndex(l, r);
    return euler[idx];
  }

  class SparseTable {
    int[][] st;
    int[] log;

    public SparseTable(int[] arr) {
      int n = arr.length, K = (int) (Math.log(n) / Math.log(2)) + 1;
      st = new int[n][K];
      log = new int[n + 1];
      for (int i = 0; i < n; i++)
        st[i][0] = i;
      for (int j = 1; j < K; j++)
        for (int i = 0; i + (1 << j) <= n; i++)
          st[i][j] = arr[st[i][j - 1]] < arr[st[i + (1 << (j - 1))][j - 1]] ? st[i][j - 1]
              : st[i + (1 << (j - 1))][j - 1];
      for (int i = 2; i <= n; i++)
        log[i] = log[i / 2] + 1;
    }

    public int queryIndex(int l, int r) {
      int j = log[r - l + 1];
      int left = st[l][j], right = st[r - (1 << j) + 1][j];
      return (euler[left] < euler[right]) ? left : right;
    }
  }
}