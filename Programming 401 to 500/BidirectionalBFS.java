
// 407_BidirectionalBFS.java
import java.util.*;

public class BidirectionalBFS {
  public static int bidirectionalBFS(int[][] graph, int start, int end) {
    int n = graph.length;
    if (start == end)
      return 0;
    Queue<Integer> q1 = new LinkedList<>(), q2 = new LinkedList<>();
    Map<Integer, Integer> dist1 = new HashMap<>(), dist2 = new HashMap<>();
    q1.add(start);
    dist1.put(start, 0);
    q2.add(end);
    dist2.put(end, 0);
    while (!q1.isEmpty() && !q2.isEmpty()) {
      if (q1.size() <= q2.size()) {
        int u = q1.poll();
        int d = dist1.get(u);
        for (int v : graph[u]) {
          if (dist1.containsKey(v))
            continue;
          if (dist2.containsKey(v))
            return d + 1 + dist2.get(v);
          dist1.put(v, d + 1);
          q1.add(v);
        }
      } else {
        int u = q2.poll();
        int d = dist2.get(u);
        for (int v : graph[u]) {
          if (dist2.containsKey(v))
            continue;
          if (dist1.containsKey(v))
            return d + 1 + dist1.get(v);
          dist2.put(v, d + 1);
          q2.add(v);
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[][] graph = { { 1, 2 }, { 0, 3 }, { 0, 3 }, { 1, 2 } };
    System.out.println(bidirectionalBFS(graph, 0, 3)); // 2
  }
}