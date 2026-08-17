
// 481_OfflineLCA.java
import java.util.*;

public class OfflineLCA {

  private int[] parent;
  private int[] ancestor;
  private boolean[] visited;

  private List<Integer>[] adj;
  private List<Query>[] queries;

  private int[] answer;

  static class Query {
    int node;
    int index;

    Query(int node, int index) {
      this.node = node;
      this.index = index;
    }
  }

  public OfflineLCA(int n, List<Integer>[] adj) {
    this.adj = adj;

    parent = new int[n];
    ancestor = new int[n];
    visited = new boolean[n];
    queries = new ArrayList[n];

    for (int i = 0; i < n; i++) {
      parent[i] = i;
      ancestor[i] = i;
      queries[i] = new ArrayList<>();
    }
  }

  // Finds representative of DSU
  private int find(int x) {
    if (parent[x] != x)
      parent[x] = find(parent[x]);
    return parent[x];
  }

  // Union two sets
  private void union(int u, int v) {
    parent[find(v)] = find(u);
  }

  // Tarjan DFS
  private void dfs(int u, int par) {

    ancestor[find(u)] = u;

    for (int v : adj[u]) {

      if (v == par)
        continue;

      dfs(v, u);

      union(u, v);

      ancestor[find(u)] = u;
    }

    visited[u] = true;

    for (Query q : queries[u]) {

      if (visited[q.node]) {
        answer[q.index] = ancestor[find(q.node)];
      }
    }
  }

  /*
   * qs[i] = {u,v}
   * Returns LCA for each query.
   */
  public int[] answerQueries(int[][] qs, int root) {

    answer = new int[qs.length];

    for (int i = 0; i < qs.length; i++) {

      int u = qs[i][0];
      int v = qs[i][1];

      queries[u].add(new Query(v, i));
      queries[v].add(new Query(u, i));
    }

    dfs(root, -1);

    return answer;
  }

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter number of nodes: ");
    int n = sc.nextInt();

    List<Integer>[] adj = new ArrayList[n];

    for (int i = 0; i < n; i++)
      adj[i] = new ArrayList<>();

    System.out.println("Enter " + (n - 1) + " edges:");

    for (int i = 0; i < n - 1; i++) {

      int u = sc.nextInt();
      int v = sc.nextInt();

      adj[u].add(v);
      adj[v].add(u);
    }

    OfflineLCA solver = new OfflineLCA(n, adj);

    System.out.print("Enter root: ");
    int root = sc.nextInt();

    System.out.print("Enter number of queries: ");
    int q = sc.nextInt();

    int[][] queries = new int[q][2];

    System.out.println("Enter queries (u v):");

    for (int i = 0; i < q; i++) {
      queries[i][0] = sc.nextInt();
      queries[i][1] = sc.nextInt();
    }

    int[] ans = solver.answerQueries(queries, root);

    System.out.println("\nLCA Results:");

    for (int i = 0; i < q; i++) {
      System.out.println(
          "LCA(" +
              queries[i][0] +
              ", " +
              queries[i][1] +
              ") = " +
              ans[i]);
    }

    sc.close();
  }
}