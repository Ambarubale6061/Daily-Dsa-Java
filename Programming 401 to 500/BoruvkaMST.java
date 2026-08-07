
// 419_BoruvkaMST.java
import java.util.*;

public class BoruvkaMST {
  public static int boruvkaMST(int n, int[][] edges) {
    DSU dsu = new DSU(n);
    int mstWeight = 0, components = n;
    while (components > 1) {
      int[] cheapest = new int[n];
      Arrays.fill(cheapest, -1);
      for (int i = 0; i < edges.length; i++) {
        int u = edges[i][0], v = edges[i][1], w = edges[i][2];
        int setU = dsu.find(u), setV = dsu.find(v);
        if (setU == setV)
          continue;
        if (cheapest[setU] == -1 || edges[cheapest[setU]][2] > w)
          cheapest[setU] = i;
        if (cheapest[setV] == -1 || edges[cheapest[setV]][2] > w)
          cheapest[setV] = i;
      }
      for (int i = 0; i < n; i++) {
        if (cheapest[i] != -1) {
          int u = edges[cheapest[i]][0], v = edges[cheapest[i]][1], w = edges[cheapest[i]][2];
          if (dsu.union(u, v)) {
            mstWeight += w;
            components--;
          }
        }
      }
    }
    return mstWeight;
  }

  static class DSU {
    int[] parent, rank;

    DSU(int n) {
      parent = new int[n];
      rank = new int[n];
      for (int i = 0; i < n; i++)
        parent[i] = i;
    }

    int find(int x) {
      return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    boolean union(int x, int y) {
      int px = find(x), py = find(y);
      if (px == py)
        return false;
      if (rank[px] < rank[py])
        parent[px] = py;
      else if (rank[px] > rank[py])
        parent[py] = px;
      else {
        parent[py] = px;
        rank[px]++;
      }
      return true;
    }
  }
}