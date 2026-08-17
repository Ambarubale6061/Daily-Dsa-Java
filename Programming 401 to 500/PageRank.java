
// 475_PageRank.java
import java.util.*;

public class PageRank {
  public static double[] pageRank(int n, List<Integer>[] adj, double d, int iterations) {
    double[] rank = new double[n];
    Arrays.fill(rank, 1.0 / n);
    for (int it = 0; it < iterations; it++) {
      double[] newRank = new double[n];
      double danglingSum = 0;
      for (int i = 0; i < n; i++) {
        if (adj[i].isEmpty())
          danglingSum += rank[i] / n;
      }
      for (int i = 0; i < n; i++) {
        newRank[i] = (1 - d) / n + d * danglingSum;
        for (int j = 0; j < n; j++) {
          if (adj[j].contains(i))
            newRank[i] += d * rank[j] / adj[j].size();
        }
      }
      rank = newRank;
    }
    return rank;
  }
}