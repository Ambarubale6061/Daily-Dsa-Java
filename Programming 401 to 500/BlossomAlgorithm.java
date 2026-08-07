
// 404_BlossomAlgorithm.java
import java.util.*;

public class BlossomAlgorithm {
  // Edmonds' Blossom Algorithm for maximum matching in general graph
  static final int N = 500;
  int n;
  boolean[][] graph;
  int[] match, p, base, q;
  boolean[] used, blossom;

  public BlossomAlgorithm(int n) {
    this.n = n;
    graph = new boolean[n][n];
    match = new int[n];
    p = new int[n];
    base = new int[n];
    q = new int[n];
    used = new boolean[n];
    blossom = new boolean[n];
  }

  public void addEdge(int u, int v) {
    graph[u][v] = graph[v][u] = true;
  }

  int lca(int a, int b) {
    boolean[] used = new boolean[n];
    for (;;) {
      a = base[a];
      used[a] = true;
      if (match[a] == -1)
        break;
      a = p[match[a]];
    }
    for (;;) {
      b = base[b];
      if (used[b])
        return b;
      b = p[match[b]];
    }
  }

  void markPath(int v, int b, int children) {
    while (base[v] != b) {
      blossom[base[v]] = blossom[base[match[v]]] = true;
      p[v] = children;
      children = match[v];
      v = p[match[v]];
    }
  }

  int findPath(int root) {
    Arrays.fill(used, false);
    Arrays.fill(p, -1);
    for (int i = 0; i < n; i++)
      base[i] = i;
    used[root] = true;
    int head = 0, tail = 0;
    q[tail++] = root;
    while (head < tail) {
      int v = q[head++];
      for (int to = 0; to < n; to++) {
        if (!graph[v][to] || base[v] == base[to] || match[v] == to)
          continue;
        if (to == root || (match[to] != -1 && p[match[to]] != -1)) {
          int curbase = lca(v, to);
          Arrays.fill(blossom, 0, n, false);
          markPath(v, curbase, to);
          markPath(to, curbase, v);
          for (int i = 0; i < n; i++) {
            if (blossom[base[i]]) {
              base[i] = curbase;
              if (!used[i]) {
                used[i] = true;
                q[tail++] = i;
              }
            }
          }
        } else if (p[to] == -1) {
          p[to] = v;
          if (match[to] == -1)
            return to;
          used[match[to]] = true;
          q[tail++] = match[to];
        }
      }
    }
    return -1;
  }

  public int maxMatching() {
    Arrays.fill(match, -1);
    for (int i = 0; i < n; i++) {
      if (match[i] == -1) {
        int v = findPath(i);
        while (v != -1) {
          int pv = p[v], ppv = match[pv];
          match[v] = pv;
          match[pv] = v;
          v = ppv;
        }
      }
    }
    int count = 0;
    for (int i = 0; i < n; i++)
      if (match[i] != -1)
        count++;
    return count / 2;
  }
  // test omitted for brevity
}