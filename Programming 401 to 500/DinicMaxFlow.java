
// 401_DinicMaxFlow.java
import java.util.*;

public class DinicMaxFlow {
  static class Edge {
    int to, rev, cap;

    Edge(int to, int rev, int cap) {
      this.to = to;
      this.rev = rev;
      this.cap = cap;
    }
  }

  List<Edge>[] graph;
  int[] level, it;

  public DinicMaxFlow(int n) {
    graph = new ArrayList[n];
    for (int i = 0; i < n; i++)
      graph[i] = new ArrayList<>();
  }

  public void addEdge(int from, int to, int cap) {
    graph[from].add(new Edge(to, graph[to].size(), cap));
    graph[to].add(new Edge(from, graph[from].size() - 1, 0));
  }

  boolean bfs(int s, int t) {
    level = new int[graph.length];
    Arrays.fill(level, -1);
    Queue<Integer> q = new LinkedList<>();
    level[s] = 0;
    q.add(s);
    while (!q.isEmpty()) {
      int v = q.poll();
      for (Edge e : graph[v]) {
        if (e.cap > 0 && level[e.to] < 0) {
          level[e.to] = level[v] + 1;
          q.add(e.to);
        }
      }
    }
    return level[t] >= 0;
  }

  int dfs(int v, int t, int f) {
    if (v == t)
      return f;
    for (; it[v] < graph[v].size(); it[v]++) {
      Edge e = graph[v].get(it[v]);
      if (e.cap > 0 && level[v] < level[e.to]) {
        int d = dfs(e.to, t, Math.min(f, e.cap));
        if (d > 0) {
          e.cap -= d;
          graph[e.to].get(e.rev).cap += d;
          return d;
        }
      }
    }
    return 0;
  }

  public int maxFlow(int s, int t) {
    int flow = 0;
    while (bfs(s, t)) {
      it = new int[graph.length];
      int f;
      while ((f = dfs(s, t, Integer.MAX_VALUE)) > 0)
        flow += f;
    }
    return flow;
  }

  public static void main(String[] args) {
    DinicMaxFlow dinic = new DinicMaxFlow(4);
    dinic.addEdge(0, 1, 10);
    dinic.addEdge(0, 2, 5);
    dinic.addEdge(1, 2, 15);
    dinic.addEdge(1, 3, 10);
    dinic.addEdge(2, 3, 10);
    System.out.println(dinic.maxFlow(0, 3)); // 15
  }
}