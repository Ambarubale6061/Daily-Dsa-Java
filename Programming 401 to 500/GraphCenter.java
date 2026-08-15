
// 472_GraphCenter.java
import java.util.*;

public class GraphCenter {
  public static List<Integer> findCenters(int n, List<Integer>[] adj) {
    int[] degree = new int[n];
    List<Integer> leaves = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      degree[i] = adj[i].size();
      if (degree[i] <= 1)
        leaves.add(i);
    }
    int remaining = n;
    while (remaining > 2) {
      List<Integer> newLeaves = new ArrayList<>();
      remaining -= leaves.size();
      for (int leaf : leaves) {
        for (int neighbor : adj[leaf]) {
          if (--degree[neighbor] == 1)
            newLeaves.add(neighbor);
        }
      }
      leaves = newLeaves;
    }
    return leaves;
  }
}