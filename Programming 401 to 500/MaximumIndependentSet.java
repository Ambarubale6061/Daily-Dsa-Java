// 425_MaximumIndependentSet.java
public class MaximumIndependentSet {
  // In bipartite graph, max independent set = total vertices - min vertex cover
  public static int maxIndependentSet(int n, int m, List<Integer>[] adj) {
    int matching = MaximumBipartiteMatching.hopcroftKarp(n, m, adj);
    return n + m - matching;
  }
}