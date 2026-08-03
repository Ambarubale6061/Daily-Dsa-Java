
// 410_EulerPath.java
import java.util.*;

public class EulerPath {
  public static List<Integer> eulerPathUndirected(int n, List<int[]> edges) {
    // assume graph is connected and has 0 or 2 odd degree vertices
    int[] deg = new int[n];
    List<Integer>[] adj = new ArrayList[n];
    for (int i = 0; i < n; i++)
      adj[i] = new ArrayList<>();
    for (int[] e : edges) {
      adj[e[0]].add(e[1]);
      adj[e[1]].add(e[0]);
      deg[e[0]]++;
      deg[e[1]]++;
    }
    int start = 0;
    for (int i = 0; i < n; i++)
      if (deg[i] % 2 == 1) {
        start = i;
        break;
      }
    Stack<Integer> stack = new Stack<>();
    List<Integer> path = new ArrayList<>();
    stack.push(start);
    while (!stack.isEmpty()) {
      int v = stack.peek();
      if (deg[v] == 0) {
        path.add(v);
        stack.pop();
      } else {
        int u = adj[v].remove(adj[v].size() - 1);
        deg[v]--;
        deg[u]--;
        stack.push(u);
      }
    }
    return path;
  }
}