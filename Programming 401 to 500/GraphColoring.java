// 420_GraphColoring.java
import java.util.*;

public class GraphColoring {
  public static int[] greedyColoring(int n, List<Integer>[] adj) {
    int[] color = new int[n];
    Arrays.fill(color, -1);
    boolean[] available = new boolean[n];
    for (int u = 0; u < n; u++) {
      Arrays.fill(available, true);
      for (int v : adj[u])
        if (color[v] != -1)
          available[color[v]] = false;
      int cr = 0;
      while (!available[cr])
        cr++;
      color[u] = cr;
    }
    return color;
  }
}