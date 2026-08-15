
// 473_GraphRadius.java
import java.util.*;

public class GraphRadius {

  // Radius = minimum eccentricity of all vertices
  public static int radius(int n, List<Integer>[] adj) {
    int radius = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      int[] dist = bfs(i, n, adj);

      int eccentricity = 0;

      for (int d : dist) {
        if (d == -1) {
          // Graph is disconnected
          return -1;
        }
        eccentricity = Math.max(eccentricity, d);
      }

      radius = Math.min(radius, eccentricity);
    }

    return radius;
  }

  // BFS from one source
  private static int[] bfs(int src, int n, List<Integer>[] adj) {
    int[] dist = new int[n];
    Arrays.fill(dist, -1);

    Queue<Integer> queue = new LinkedList<>();
    queue.offer(src);
    dist[src] = 0;

    while (!queue.isEmpty()) {
      int node = queue.poll();

      for (int neighbor : adj[node]) {
        if (dist[neighbor] == -1) {
          dist[neighbor] = dist[node] + 1;
          queue.offer(neighbor);
        }
      }
    }

    return dist;
  }

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter number of vertices: ");
    int n = sc.nextInt();

    System.out.print("Enter number of edges: ");
    int m = sc.nextInt();

    List<Integer>[] adj = new ArrayList[n];

    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<>();
    }

    System.out.println("Enter edges (0-based indexing):");

    for (int i = 0; i < m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();

      adj[u].add(v);
      adj[v].add(u);
    }

    int ans = radius(n, adj);

    if (ans == -1) {
      System.out.println("Graph is disconnected.");
    } else {
      System.out.println("Graph Radius = " + ans);
    }

    sc.close();
  }
}