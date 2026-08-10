// 424_MinimumVertexCover.java
public class MinimumVertexCover {
  // In bipartite graph, min vertex cover = max matching (Kőnig's theorem)
  public static int minVertexCover(int n, int m, List<Integer>[] adj) {
    return MaximumBipartiteMatching.hopcroftKarp(n, m, adj);
  }
}