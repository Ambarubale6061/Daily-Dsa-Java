
// 414_ChinesePostman.java
import java.util.*;

public class ChinesePostman {
  public static int chinesePostman(int n, int[][] edges) {
    // Simplified for undirected connected graph: add edges to make all degrees
    // even, then Euler circuit length.
    // Uses Floyd Warshall for shortest path, then minimum weight perfect matching
    // on odd-degree nodes.
    int[][] dist = new int[n][n];
    for (int i = 0; i < n; i++)
      Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
    int total = 0;
    for (int[] e : edges) {
      dist[e[0]][e[1]] = Math.min(dist[e[0]][e[1]], e[2]);
      dist[e[1]][e[0]] = Math.min(dist[e[1]][e[0]], e[2]);
      total += e[2];
    }
    for (int k = 0; k < n; k++)
      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
          if (dist[i][k] + dist[k][j] < dist[i][j])
            dist[i][j] = dist[i][k] + dist[k][j];
    List<Integer> odd = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int deg = 0;
      for (int[] e : edges)
        if (e[0] == i || e[1] == i)
          deg++;
      if (deg % 2 == 1)
        odd.add(i);
    }
    int m = odd.size();
    int[][] dp = new int[1 << m][m];
    // DP for minimum weight perfect matching (simplified)
    int extra = 0;
    if (m > 0) {
      int[] dp2 = new int[1 << m];
      Arrays.fill(dp2, Integer.MAX_VALUE / 2);
      dp2[0] = 0;
      for (int mask = 0; mask < (1 << m); mask++) {
        int first = -1;
        for (int i = 0; i < m; i++)
          if ((mask & (1 << i)) == 0) {
            first = i;
            break;
          }
        if (first == -1)
          continue;
        for (int j = first + 1; j < m; j++) {
          if ((mask & (1 << j)) == 0) {
            int newMask = mask | (1 << first) | (1 << j);
            dp2[newMask] = Math.min(dp2[newMask], dp2[mask] + dist[odd.get(first)][odd.get(j)]);
          }
        }
      }
      extra = dp2[(1 << m) - 1];
    }
    return total + extra;
  }
}