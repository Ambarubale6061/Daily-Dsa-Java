
// 411_EulerCircuit.java
import java.util.*;

public class EulerCircuit {
  public static List<Integer> eulerCircuitDirected(int n, List<int[]> edges) {
    // must be balanced and strongly connected ignoring isolated vertices
    int[] indeg = new int[n], outdeg = new int[n];
    List<Integer>[] adj = new ArrayList[n];
    for (int i = 0; i < n; i++)
      adj[i] = new ArrayList<>();
    for (int[] e : edges) {
      adj[e[0]].add(e[1]);
      outdeg[e[0]]++;
      indeg[e[1]]++;
    }
    // find start with outdeg > 0
    int start = 0;
    while (start < n && outdeg[start] == 0)
      start++;
    Stack<Integer> stack = new Stack<>();
    List<Integer> path = new ArrayList<>();
    stack.push(start);
    while (!stack.isEmpty()) {
      int v = stack.peek();
      if (outdeg[v] == 0) {
        path.add(v);
        stack.pop();
      } else {
        int u = adj[v].remove(adj[v].size() - 1);
        outdeg[v]--;
        stack.push(u);
      }
    }
    Collections.reverse(path);
    return path;
  }
}