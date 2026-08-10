
// 433_TreeDP.java
import java.util.*;

public class TreeDP {
  // Example: Maximum sum of independent set on tree
  public static int maxIndependentSet(int n, List<Integer>[] adj, int[] weight) {
    int[][] dp = new int[n][2];
    dfs(0, -1, adj, weight, dp);
    return Math.max(dp[0][0], dp[0][1]);
  }

  static void dfs(int u, int p, List<Integer>[] adj, int[] weight, int[][] dp) {
    dp[u][0] = 0;
    dp[u][1] = weight[u];
    for (int v : adj[u]) {
      if (v == p)
        continue;
      dfs(v, u, adj, weight, dp);
      dp[u][0] += Math.max(dp[v][0], dp[v][1]);
      dp[u][1] += dp[v][0];
    }
  }
}