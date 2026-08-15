
// 474_BetweennessCentrality.java
import java.util.*;

public class BetweennessCentrality {

  /*
   * Brandes Algorithm
   * Computes betweenness centrality for every vertex
   * Time Complexity: O(V * (V + E)) for unweighted graphs
   */
  public double[] compute(int n, List<Integer>[] adj) {

    double[] BC = new double[n];

    for (int s = 0; s < n; s++) {

      Stack<Integer> stack = new Stack<>();

      List<Integer>[] pred = new ArrayList[n];
      for (int i = 0; i < n; i++)
        pred[i] = new ArrayList<>();

      int[] dist = new int[n];
      Arrays.fill(dist, -1);

      double[] sigma = new double[n];
      sigma[s] = 1;

      Queue<Integer> queue = new LinkedList<>();
      queue.offer(s);
      dist[s] = 0;

      // BFS
      while (!queue.isEmpty()) {
        int v = queue.poll();
        stack.push(v);

        for (int w : adj[v]) {

          if (dist[w] < 0) {
            dist[w] = dist[v] + 1;
            queue.offer(w);
          }

          if (dist[w] == dist[v] + 1) {
            sigma[w] += sigma[v];
            pred[w].add(v);
          }
        }
      }

      double[] delta = new double[n];

      // Dependency accumulation
      while (!stack.isEmpty()) {
        int w = stack.pop();

        for (int v : pred[w]) {
          delta[v] += (sigma[v] / sigma[w]) * (1.0 + delta[w]);
        }

        if (w != s) {
          BC[w] += delta[w];
        }
      }
    }

    // Undirected graph adjustment
    for (int i = 0; i < n; i++) {
      BC[i] /= 2.0;
    }

    return BC;
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

    BetweennessCentrality bc = new BetweennessCentrality();
    double[] result = bc.compute(n, adj);

    System.out.println("\nBetweenness Centrality:");

    for (int i = 0; i < n; i++) {
      System.out.printf("Vertex %d : %.2f%n", i, result[i]);
    }

    sc.close();
  }
}