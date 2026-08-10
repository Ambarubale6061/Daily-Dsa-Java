
// 421_BiconnectedComponents.java
import java.util.*;

public class BiconnectedComponents {
  static int time = 0;
  static List<List<int[]>> bcc; // edges list
  static Stack<int[]> stack;

  public static List<List<int[]>> findBCC(int n, List<Integer>[] adj) {
    bcc = new ArrayList<>();
    stack = new Stack<>();
    int[] disc = new int[n], low = new int[n];
    Arrays.fill(disc, -1);
    for (int i = 0; i < n; i++)
      if (disc[i] == -1)
        dfs(adj, i, -1, disc, low);
    return bcc;
  }

  static void dfs(List<Integer>[] adj, int u, int parent, int[] disc, int[] low) {
    disc[u] = low[u] = ++time;
    int children = 0;
    for (int v : adj[u]) {
      if (v == parent)
        continue;
      if (disc[v] == -1) {
        stack.push(new int[] { u, v });
        children++;
        dfs(adj, v, u, disc, low);
        low[u] = Math.min(low[u], low[v]);
        if ((parent == -1 && children >= 2) || (parent != -1 && low[v] >= disc[u])) {
          List<int[]> comp = new ArrayList<>();
          while (!stack.isEmpty() && !(stack.peek()[0] == u && stack.peek()[1] == v))
            comp.add(stack.pop());
          comp.add(stack.pop());
          bcc.add(comp);
        }
      } else if (disc[v] < disc[u]) {
        low[u] = Math.min(low[u], disc[v]);
        stack.push(new int[] { u, v });
      }
    }
  }
}