// 485_GomoryHuTree.java
// Gomory-Hu Tree Implementation using Edmonds-Karp Max Flow
// Time Complexity: O(V * E^2 * V) (using Edmonds-Karp)
// Suitable for educational purposes.

import java.util.*;

public class GomoryHuTree {

    private int[][] capacity;
    private int[][] residual;
    private int n;

    // Parent of each vertex in Gomory-Hu Tree
    private int[] parent;

    // Min-cut value between node and parent
    private int[] flow;

    /**
     * Builds Gomory-Hu Tree.
     *
     * @param n   Number of vertices
     * @param cap Capacity matrix (undirected graph)
     * @return Tree adjacency matrix where tree[u][v] = min-cut weight
     */
    public int[][] build(int n, int[][] cap) {
        this.n = n;
        this.capacity = cap;

        parent = new int[n];
        flow = new int[n];

        Arrays.fill(parent, 0);

        for (int s = 1; s < n; s++) {

            int t = parent[s];

            int cutValue = maxFlow(s, t);
            flow[s] = cutValue;

            boolean[] side = minCutSide(s);

            for (int i = s + 1; i < n; i++) {
                if (parent[i] == t && side[i]) {
                    parent[i] = s;
                }
            }

            if (side[parent[t]]) {

                parent[s] = parent[t];
                parent[t] = s;

                int temp = flow[s];
                flow[s] = flow[t];
                flow[t] = temp;
            }
        }

        int[][] tree = new int[n][n];

        for (int i = 1; i < n; i++) {
            tree[i][parent[i]] = flow[i];
            tree[parent[i]][i] = flow[i];
        }

        return tree;
    }

    // -------------------------
    // Edmonds-Karp Maximum Flow
    // -------------------------
    private int maxFlow(int source, int sink) {

        residual = new int[n][n];

        for (int i = 0; i < n; i++)
            residual[i] = capacity[i].clone();

        int maxFlow = 0;

        while (true) {

            int[] parent = new int[n];
            Arrays.fill(parent, -1);

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(source);
            parent[source] = source;

            while (!queue.isEmpty() && parent[sink] == -1) {

                int u = queue.poll();

                for (int v = 0; v < n; v++) {
                    if (parent[v] == -1 && residual[u][v] > 0) {
                        parent[v] = u;
                        queue.offer(v);
                    }
                }
            }

            if (parent[sink] == -1)
                break;

            int bottleneck = Integer.MAX_VALUE;

            for (int v = sink; v != source; v = parent[v]) {
                bottleneck = Math.min(bottleneck, residual[parent[v]][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                residual[parent[v]][v] -= bottleneck;
                residual[v][parent[v]] += bottleneck;
            }

            maxFlow += bottleneck;
        }

        return maxFlow;
    }

    // -------------------------
    // BFS on Residual Graph
    // -------------------------
    private boolean[] minCutSide(int source) {

        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {

            int u = queue.poll();

            for (int v = 0; v < n; v++) {

                if (!visited[v] && residual[u][v] > 0) {
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }

        return visited;
    }

    // -------------------------
    // Print Tree
    // -------------------------
    public static void printTree(int[][] tree) {

        System.out.println("Gomory-Hu Tree Edges:");

        for (int i = 0; i < tree.length; i++) {
            for (int j = i + 1; j < tree.length; j++) {
                if (tree[i][j] != 0) {
                    System.out.println(i + " --(" + tree[i][j] + ")--> " + j);
                }
            }
        }
    }

    // -------------------------
    // Example
    // -------------------------
    public static void main(String[] args) {

        int[][] graph = {
                {0, 3, 2, 0},
                {3, 0, 4, 2},
                {2, 4, 0, 5},
                {0, 2, 5, 0}
        };

        GomoryHuTree gh = new GomoryHuTree();

        int[][] tree = gh.build(4, graph);

        printTree(tree);
    }
}