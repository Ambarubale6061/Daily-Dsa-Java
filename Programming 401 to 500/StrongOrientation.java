
// 476_StrongOrientation.java
import java.util.*;

public class StrongOrientation {

  private static int timer;
  private static int[] tin;
  private static int[] low;
  private static boolean[] visited;

  // Stores directed edges (u -> v)
  private static List<int[]> result;

  /*
   * Orient edges so that the graph becomes strongly connected.
   * If the graph contains a bridge, strong orientation is impossible.
   * Returns:
   * List of directed edges if possible
   * null otherwise
   */
  public static List<int[]> orient(int n, List<Integer>[] adj) {

    timer = 0;
    tin = new int[n];
    low = new int[n];
    visited = new boolean[n];
    result = new ArrayList<>();

    dfs(0, -1, adj);

    // Graph must be connected
    for (boolean v : visited) {
      if (!v)
        return null;
    }

    return result;
  }

  private static boolean dfs(int u, int parent, List<Integer>[] adj) {

    visited[u] = true;
    tin[u] = low[u] = ++timer;

    for (int v : adj[u]) {

      if (v == parent)
        continue;

      if (!visited[v]) {

        // Tree Edge
        result.add(new int[] { u, v });

        if (!dfs(v, u, adj))
          return false;

        low[u] = Math.min(low[u], low[v]);

        // Bridge found
        if (low[v] > tin[u]) {
          return false;
        }

      } else {

        low[u] = Math.min(low[u], tin[v]);

        // Back Edge
        if (tin[v] < tin[u]) {
          result.add(new int[] { u, v });
        }
      }
    }

    return true;
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

    System.out.println("Enter edges (0-based indexing):");

    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();

      adj[u].add(v);
      adj[v].add(u);
    }

    List<int[]> orientation = orient(n, adj);

    if (orientation == null) {
      System.out.println("\nStrong orientation is NOT possible (graph has a bridge or is disconnected).");
    } else {
      System.out.println("\nDirected Edges:");
      for (int[] edge : orientation) {
        System.out.println(edge[0] + " -> " + edge[1]);
      }
    }

    sc.close();
  }
}