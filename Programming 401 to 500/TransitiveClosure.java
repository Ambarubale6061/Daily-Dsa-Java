
// 480_TransitiveClosure.java
import java.util.*;

public class TransitiveClosure {
  public static boolean[][] warshall(int n, boolean[][] adj) {
    boolean[][] reach = new boolean[n][n];
    for (int i = 0; i < n; i++)
      System.arraycopy(adj[i], 0, reach[i], 0, n);
    for (int k = 0; k < n; k++)
      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
          if (reach[i][k] && reach[k][j])
            reach[i][j] = true;
    return reach;
  }
}