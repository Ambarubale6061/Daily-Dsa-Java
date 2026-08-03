
// 402_FordFulkerson.java
import java.util.*;

public class FordFulkerson {
  static final int INF = Integer.MAX_VALUE;
  int[][] capacity;
  int[] parent;
  int n;

  public FordFulkerson(int n) {
    this.n = n;
    capacity = new int[n][n];
    parent = new int[n];
  }

  public void addEdge(int u, int v, int cap) {
    capacity[u][v] += cap; // allow multiple edges
  }

  boolean bfs(int s, int t) {
    Arrays.fill(parent, -1);
    Queue<Integer> q = new LinkedList<>();
    q.add(s);
    parent[s] = s;
    while (!q.isEmpty()) {
      int u = q.poll();
      for (int v = 0; v < n; v++) {
        if (parent[v] == -1 && capacity[u][v] > 0) {
          parent[v] = u;
          if (v == t)
            return true;
          q.add(v);
        }
      }
    }
    return false;
  }

  public int maxFlow(int s, int t) {
    int flow = 0;
    while (bfs(s, t)) {
      int pathFlow = INF;
      for (int v = t; v != s; v = parent[v]) {
        int u = parent[v];
        pathFlow = Math.min(pathFlow, capacity[u][v]);
      }
      for (int v = t; v != s; v = parent[v]) {
        int u = parent[v];
        capacity[u][v] -= pathFlow;
        capacity[v][u] += pathFlow;
      }
      flow += pathFlow;
    }
    return flow;
  }

  public static void main(String[] args) {
    FordFulkerson ff = new FordFulkerson(4);
    ff.addEdge(0, 1, 10);
    ff.addEdge(0, 2, 5);
    ff.addEdge(1, 2, 15);
    ff.addEdge(1, 3, 10);
    ff.addEdge(2, 3, 10);
    System.out.println(ff.maxFlow(0, 3)); // 15
  }
}