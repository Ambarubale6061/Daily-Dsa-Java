
// 432_HLDQueries.java
import java.util.*;

public class HLDQueries {
  // Heavy-Light Decomposition with segment tree for path sum queries
  List<Integer>[] adj;
  int[] parent, depth, heavy, head, pos, sz;
  int curPos;
  SegmentTree segTree;
  int[] values;

  public HLDQueries(int n, int[] initValues) {
    adj = new ArrayList[n];
    for (int i = 0; i < n; i++)
      adj[i] = new ArrayList<>();
    values = initValues.clone();
    parent = new int[n];
    depth = new int[n];
    heavy = new int[n];
    head = new int[n];
    pos = new int[n];
    sz = new int[n];
    Arrays.fill(heavy, -1);
  }

  public void addEdge(int u, int v) {
    adj[u].add(v);
    adj[v].add(u);
  }

  public void build(int root) {
    dfs(root);
    curPos = 0;
    decompose(root, root);
    int[] segArr = new int[adj.length];
    for (int i = 0; i < adj.length; i++)
      segArr[pos[i]] = values[i];
    segTree = new SegmentTree(segArr);
  }

  int dfs(int u) {
    sz[u] = 1;
    int maxSize = 0;
    for (int v : adj[u]) {
      if (v == parent[u])
        continue;
      parent[v] = u;
      depth[v] = depth[u] + 1;
      int childSize = dfs(v);
      sz[u] += childSize;
      if (childSize > maxSize) {
        maxSize = childSize;
        heavy[u] = v;
      }
    }
    return sz[u];
  }

  void decompose(int u, int h) {
    head[u] = h;
    pos[u] = curPos++;
    if (heavy[u] != -1)
      decompose(heavy[u], h);
    for (int v : adj[u])
      if (v != parent[u] && v != heavy[u])
        decompose(v, v);
  }

  public int queryPath(int u, int v) {
    int res = 0;
    while (head[u] != head[v]) {
      if (depth[head[u]] < depth[head[v]]) {
        int t = u;
        u = v;
        v = t;
      }
      res += segTree.query(pos[head[u]], pos[u]);
      u = parent[head[u]];
    }
    if (depth[u] > depth[v]) {
      int t = u;
      u = v;
      v = t;
    }
    res += segTree.query(pos[u], pos[v]);
    return res;
  }

  public void updateNode(int u, int val) {
    segTree.update(pos[u], val);
  }

  class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int[] arr) {
      n = arr.length;
      tree = new int[4 * n];
      build(0, 0, n - 1, arr);
    }

    void build(int node, int l, int r, int[] arr) {
      if (l == r) {
        tree[node] = arr[l];
        return;
      }
      int mid = (l + r) / 2;
      build(2 * node + 1, l, mid, arr);
      build(2 * node + 2, mid + 1, r, arr);
      tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    void update(int node, int l, int r, int idx, int val) {
      if (l == r) {
        tree[node] = val;
        return;
      }
      int mid = (l + r) / 2;
      if (idx <= mid)
        update(2 * node + 1, l, mid, idx, val);
      else
        update(2 * node + 2, mid + 1, r, idx, val);
      tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    void update(int idx, int val) {
      update(0, 0, n - 1, idx, val);
    }

    int query(int node, int l, int r, int ql, int qr) {
      if (ql > r || qr < l)
        return 0;
      if (ql <= l && r <= qr)
        return tree[node];
      int mid = (l + r) / 2;
      return query(2 * node + 1, l, mid, ql, qr) + query(2 * node + 2, mid + 1, r, ql, qr);
    }

    int query(int l, int r) {
      return query(0, 0, n - 1, l, r);
    }
  }
}