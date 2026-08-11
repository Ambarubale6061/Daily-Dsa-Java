
// 428_TreeIsomorphism.java
import java.util.*;

public class TreeIsomorphism {
  // AHU algorithm for rooted tree isomorphism (returns canonical form)
  public static String canonicalForm(int root, List<Integer>[] adj) {
    return dfs(root, -1, adj);
  }

  static String dfs(int u, int p, List<Integer>[] adj) {
    List<String> children = new ArrayList<>();
    for (int v : adj[u])
      if (v != p)
        children.add(dfs(v, u, adj));
    Collections.sort(children);
    StringBuilder sb = new StringBuilder("(");
    for (String s : children)
      sb.append(s);
    sb.append(")");
    return sb.toString();
  }

  public static boolean areIsomorphic(int n, List<Integer>[] adj1, List<Integer>[] adj2) {
    int c1 = TreeCentroid.findCentroids(n, adj1).get(0);
    int c2 = TreeCentroid.findCentroids(n, adj2).get(0);
    return canonicalForm(c1, adj1).equals(canonicalForm(c2, adj2));
  }
}