
// 477_DirectedMST.java
import java.util.*;

public class DirectedMST {

  /*
   * Chu–Liu/Edmonds Algorithm
   * Finds the minimum spanning arborescence rooted at 'root'.
   *
   * Edge format:
   * edges[i][0] = from
   * edges[i][1] = to
   * edges[i][2] = weight
   *
   * Returns:
   * Minimum cost of Directed MST
   * -1 if impossible
   */

  public static int directedMST(int n, int root, int[][] edges) {

    final int INF = Integer.MAX_VALUE;
    int answer = 0;

    while (true) {

      int[] in = new int[n];
      int[] pre = new int[n];

      Arrays.fill(in, INF);

      // Minimum incoming edge for every vertex
      for (int[] e : edges) {
        int u = e[0];
        int v = e[1];
        int w = e[2];

        if (u != v && w < in[v]) {
          in[v] = w;
          pre[v] = u;
        }
      }

      in[root] = 0;

      // If any node (except root) has no incoming edge
      for (int i = 0; i < n; i++) {
        if (in[i] == INF)
          return -1;
      }

      int cnt = 0;

      int[] id = new int[n];
      int[] vis = new int[n];

      Arrays.fill(id, -1);
      Arrays.fill(vis, -1);

      // Detect cycles
      for (int i = 0; i < n; i++) {

        answer += in[i];

        int v = i;

        while (vis[v] != i && id[v] == -1 && v != root) {
          vis[v] = i;
          v = pre[v];
        }

        if (v != root && id[v] == -1) {

          for (int u = pre[v]; u != v; u = pre[u])
            id[u] = cnt;

          id[v] = cnt++;
        }
      }

      // No cycles
      if (cnt == 0)
        break;

      for (int i = 0; i < n; i++) {
        if (id[i] == -1)
          id[i] = cnt++;
      }

      List<int[]> newEdges = new ArrayList<>();

      for (int[] e : edges) {

        int u = id[e[0]];
        int v = id[e[1]];
        int w = e[2];

        if (u != v) {
          newEdges.add(new int[] {
              u,
              v,
              w - in[e[1]]
          });
        }
      }

      edges = new int[newEdges.size()][3];

      for (int i = 0; i < newEdges.size(); i++)
        edges[i] = newEdges.get(i);

      n = cnt;
      root = id[root];
    }

    return answer;
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter number of vertices: ");
    int n = sc.nextInt();

    System.out.print("Enter number of edges: ");
    int m = sc.nextInt();

    System.out.print("Enter root: ");
    int root = sc.nextInt();

    int[][] edges = new int[m][3];

    System.out.println("Enter edges (from to weight):");

    for (int i = 0; i < m; i++) {
      edges[i][0] = sc.nextInt();
      edges[i][1] = sc.nextInt();
      edges[i][2] = sc.nextInt();
    }

    int ans = directedMST(n, root, edges);

    if (ans == -1)
      System.out.println("Directed MST does not exist.");
    else
      System.out.println("Minimum Directed MST Cost = " + ans);

    sc.close();
  }
}