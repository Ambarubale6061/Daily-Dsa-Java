
// 442_PersistantDSU.java
import java.util.*;

public class PersistantDSU {
  // Persistent DSU with rollbacks (not fully persistent)
  int[] parent, rank;
  Stack<int[]> history;

  public PersistantDSU(int n) {
    parent = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; i++)
      parent[i] = i;
    history = new Stack<>();
  }

  public int find(int x) {
    while (parent[x] != x)
      x = parent[x];
    return x;
  }

  public boolean union(int a, int b) {
    a = find(a);
    b = find(b);
    if (a == b)
      return false;
    if (rank[a] < rank[b]) {
      int t = a;
      a = b;
      b = t;
    }
    history.push(new int[] { b, parent[b], a, rank[a] });
    parent[b] = a;
    if (rank[a] == rank[b])
      rank[a]++;
    return true;
  }

  public void rollback() {
    int[] last = history.pop();
    parent[last[0]] = last[1];
    rank[last[2]] = last[3];
  }
}