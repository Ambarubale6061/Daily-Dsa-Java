
// 416_CentroidTree.java
import java.util.*;

public class CentroidTree {
  List<Integer>[] adj;
  int[] sz;
  boolean[] removed;
  int[] parentCentroid;

  public CentroidTree(int n, List<Integer>[] graph) {
    adj = graph;
    sz = new int[n];
    removed = new boolean[n];
    parentCentroid = new int[n];
    Arrays.fill(parentCentroid, -1);
    buildCentroidTree(0, -1);
  }

  void buildCentroidTree(int u, int p) {
    dfsSize(u, -1);
    int centroid = findCentroid(u, -1, sz[u] / 2);
    parentCentroid[centroid] = p;
    removed[centroid] = true;
    for (int v : adj[centroid])
      if (!removed[v])
        buildCentroidTree(v, centroid);
  }

  int dfsSize(int u, int p) {
    sz[u] = 1;
    for (int v : adj[u])
      if (v != p && !removed[v])
        sz[u] += dfsSize(v, u);
    return sz[u];
  }

  int findCentroid(int u, int p, int half) {
    for (int v : adj[u])
      if (v != p && !removed[v] && sz[v] > half)
        return findCentroid(v, u, half);
    return u;
  }
}