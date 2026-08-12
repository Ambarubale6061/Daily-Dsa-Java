
// 446_SCCCondensation.java
import java.util.*;

public class SCCCondensation {
  // Build condensation DAG of SCCs
  public static List<Integer>[] condense(int n, List<Integer>[] adj) {
    List<List<Integer>> sccs = KosarajuAlgorithm.kosaraju(n, adj);
    int[] comp = new int[n];
    int c = 0;
    for (List<Integer> scc : sccs)
      for (int v : scc)
        comp[v] = c;
    c = sccs.size();
    List<Integer>[] dag = new ArrayList[c];
    for (int i = 0; i < c; i++)
      dag[i] = new ArrayList<>();
    for (int u = 0; u < n; u++) {
      for (int v : adj[u]) {
        if (comp[u] != comp[v])
          dag[comp[u]].add(comp[v]);
      }
    }
    return dag;
  }
}