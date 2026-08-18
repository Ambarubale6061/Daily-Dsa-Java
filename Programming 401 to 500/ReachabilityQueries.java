
// 479_ReachabilityQueries.java
import java.util.*;

public class ReachabilityQueries {

  /*
   * Returns a boolean array where:
   * reachable[i] = true if vertex i is reachable from src.
   *
   * Uses BFS (works for directed and undirected graphs).
   */

  public static boolean[] reachable(int n, List<Integer>[] adj, int src) {

    boolean[] visited = new boolean[n];
    Queue<Integer> queue = new LinkedList<>();

    visited[src] = true;
    queue.offer(src);

    while (!queue.isEmpty()) {

      int u = queue.poll();

      for (int v : adj[u]) {
        if (!visited[v]) {
          visited[v] = true;
          queue.offer(v);
        }
      }
    }

    return visited;
  }

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter number of vertices: ");
    int n = sc.nextInt();

    System.out.print("Enter number of edges: ");
    int m = sc.nextInt();

    List<Integer>[] adj = new ArrayList[n];

    for (int i = 0; i < n; i++)
      adj[i] = new ArrayList<>();

    System.out.println("Enter directed edges (u v):");

    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();

      adj[u].add(v);
    }

    System.out.print("Enter source vertex: ");
    int src = sc.nextInt();

    boolean[] ans = reachable(n, adj, src);

    System.out.println("\nReachability:");

    for (int i = 0; i < n; i++) {
      System.out.println(src + " -> " + i + " : " + ans[i]);
    }

    sc.close();
  }
}