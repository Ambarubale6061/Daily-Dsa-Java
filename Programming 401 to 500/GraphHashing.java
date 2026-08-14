
// 456_GraphHashing.java
import java.util.*;

public class GraphHashing {
  // Merkle-tree like hashing for trees
  public static String hashTree(int u, List<Integer>[] adj, int p) {
    List<String> children = new ArrayList<>();
    for (int v : adj[u])
      if (v != p)
        children.add(hashTree(v, adj, u));
    Collections.sort(children);
    return "(" + String.join(",", children) + ")";
  }
}