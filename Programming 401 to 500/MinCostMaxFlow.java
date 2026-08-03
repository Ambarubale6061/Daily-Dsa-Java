
// 403_MinCostMaxFlow.java
import java.util.*;

public class MinCostMaxFlow {
  static class Edge {
    int to, rev, cap, cost;

    Edge(int to, int rev, int cap, int cost) {
      this.to = to;
      this.rev = rev;
      this.cap = cap;
      this.cost = cost;
    }
  }

  List<Edge>[] graph;
  int[] dist, prevv, preve;

  public MinCostMaxFlow(int n) {
    graph = new ArrayList[n];
    for (int i = 0; i < n; i++)
      graph[i] = new ArrayList<>();
  }

  public void addEdge(int from, int to, int cap, int cost) {
    graph[from].add(new Edge(to, graph[to].size(), cap, cost));
    graph[to].add(new Edge(from, graph[from].size() - 1, 0, -cost));
  }

  int[] minCostFlow(int s, int t, int maxf) {
    int flow = 0, cost = 0;
    int n = graph.length;
    while (flow < maxf) {
      dist = new int[n];
      Arrays.fill(dist, Integer.MAX_VALUE);
      dist[s] = 0;
      boolean updated;
      do {
        updated = false;
        for (int v = 0; v < n; v++) {
          if (dist[v] == Integer.MAX_VALUE)
            continue;
          for (int i = 0; i < graph[v].size(); i++) {
            Edge e = graph[v].get(i);
            if (e.cap > 0 && dist[e.to] > dist[v] + e.cost) {
              dist[e.to] = dist[v] + e.cost;
              prevv[e.to] = v;
              preve[e.to] = i;
              updated = true;
            }
          }
        }
      } while (updated);
      if (dist[t] == Integer.MAX_VALUE)
        break;
      int d = maxf - flow;
      for (int v = t; v != s; v = prevv[v])
        d = Math.min(d, graph[prevv[v]].get(preve[v]).cap);
      flow += d;
      cost += d * dist[t];
      for (int v = t; v != s; v = prevv[v]) {
        Edge e = graph[prevv[v]].get(preve[v]);
        e.cap -= d;
        graph[v].get(e.rev).cap += d;
      }
    }
    return new int[] { flow, cost };
  }

  public static void main(String[] args) {
    MinCostMaxFlow mcmf = new MinCostMaxFlow(5);
    mcmf.addEdge(0, 1, 10, 2);
    mcmf.addEdge(0, 2, 2, 4);
    mcmf.addEdge(1, 2, 6, 6);
    mcmf.addEdge(1, 3, 6, 1);
    mcmf.addEdge(2, 4, 5, 2);
    mcmf.addEdge(3, 4, 10, 3);
    int[] res = mcmf.minCostFlow(0, 4, 10);
    System.out.println(res[0] + " " + res[1]); // flow and cost
  }
}