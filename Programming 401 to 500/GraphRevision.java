// 500_GraphRevision.java
// Complete Graph Revision Program
// Covers:
// 1. Graph Representations
// 2. BFS
// 3. DFS
// 4. Cycle Detection (Undirected)
// 5. Connected Components
// 6. Topological Sort (Kahn)
// 7. Dijkstra
// 8. Disjoint Set Union (Union-Find)

import java.util.*;

public class GraphRevision {

    // -----------------------------
    // Edge Class
    // -----------------------------
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // -----------------------------
    // Graph
    // -----------------------------
    static class Graph {

        int V;
        List<List<Edge>> adj;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();

            for (int i = 0; i < V; i++)
                adj.add(new ArrayList<>());
        }

        void addEdge(int u, int v) {
            addEdge(u, v, 1);
        }

        void addEdge(int u, int v, int w) {
            adj.get(u).add(new Edge(v, w));
            adj.get(v).add(new Edge(u, w));
        }

        void addDirectedEdge(int u, int v) {
            adj.get(u).add(new Edge(v, 1));
        }

        void addDirectedEdge(int u, int v, int w) {
            adj.get(u).add(new Edge(v, w));
        }

        void printGraph() {
            for (int i = 0; i < V; i++) {
                System.out.print(i + " -> ");
                for (Edge e : adj.get(i))
                    System.out.print("(" + e.to + "," + e.weight + ") ");
                System.out.println();
            }
        }
    }

    // -----------------------------
    // BFS
    // -----------------------------
    static void bfs(Graph g, int start) {

        boolean[] vis = new boolean[g.V];
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        vis[start] = true;

        while (!q.isEmpty()) {

            int u = q.poll();
            System.out.print(u + " ");

            for (Edge e : g.adj.get(u)) {
                if (!vis[e.to]) {
                    vis[e.to] = true;
                    q.offer(e.to);
                }
            }
        }

        System.out.println();
    }

    // -----------------------------
    // DFS
    // -----------------------------
    static void dfs(Graph g, int start) {

        boolean[] vis = new boolean[g.V];
        dfsUtil(g, start, vis);
        System.out.println();
    }

    static void dfsUtil(Graph g, int u, boolean[] vis) {

        vis[u] = true;

        System.out.print(u + " ");

        for (Edge e : g.adj.get(u)) {

            if (!vis[e.to])
                dfsUtil(g, e.to, vis);
        }
    }

    // -----------------------------
    // Connected Components
    // -----------------------------
    static int connectedComponents(Graph g) {

        boolean[] vis = new boolean[g.V];

        int count = 0;

        for (int i = 0; i < g.V; i++) {

            if (!vis[i]) {
                dfsUtil(g, i, vis);
                System.out.println();
                count++;
            }
        }

        return count;
    }

    // -----------------------------
    // Cycle Detection
    // -----------------------------
    static boolean hasCycle(Graph g) {

        boolean[] vis = new boolean[g.V];

        for (int i = 0; i < g.V; i++) {

            if (!vis[i]) {

                if (cycleDFS(g, i, -1, vis))
                    return true;
            }
        }

        return false;
    }

    static boolean cycleDFS(Graph g,
                            int u,
                            int parent,
                            boolean[] vis) {

        vis[u] = true;

        for (Edge e : g.adj.get(u)) {

            if (!vis[e.to]) {

                if (cycleDFS(g, e.to, u, vis))
                    return true;

            } else if (e.to != parent)
                return true;
        }

        return false;
    }

    // -----------------------------
    // Topological Sort
    // -----------------------------
    static List<Integer> topoSort(int V,
                                  List<List<Integer>> adj) {

        int[] indegree = new int[V];

        for (int i = 0; i < V; i++)
            for (int v : adj.get(i))
                indegree[v]++;

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < V; i++)
            if (indegree[i] == 0)
                q.offer(i);

        List<Integer> order = new ArrayList<>();

        while (!q.isEmpty()) {

            int u = q.poll();

            order.add(u);

            for (int v : adj.get(u)) {

                indegree[v]--;

                if (indegree[v] == 0)
                    q.offer(v);
            }
        }

        return order;
    }

    // -----------------------------
    // Dijkstra
    // -----------------------------
    static int[] dijkstra(Graph g, int src) {

        int[] dist = new int[g.V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        dist[src] = 0;

        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            int u = cur[0];

            if (cur[1] != dist[u])
                continue;

            for (Edge e : g.adj.get(u)) {

                if (dist[e.to] > dist[u] + e.weight) {

                    dist[e.to] = dist[u] + e.weight;

                    pq.offer(new int[]{e.to, dist[e.to]});
                }
            }
        }

        return dist;
    }

    // -----------------------------
    // Disjoint Set Union
    // -----------------------------
    static class DSU {

        int[] parent;
        int[] rank;

        DSU(int n) {

            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {

            if (parent[x] != x)
                parent[x] = find(parent[x]);

            return parent[x];
        }

        void union(int a, int b) {

            int pa = find(a);
            int pb = find(b);

            if (pa == pb)
                return;

            if (rank[pa] < rank[pb])
                parent[pa] = pb;
            else if (rank[pb] < rank[pa])
                parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
        }
    }

    // -----------------------------
    // Main
    // -----------------------------
    public static void main(String[] args) {

        Graph g = new Graph(6);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 2);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 3);
        g.addEdge(4, 5, 2);

        System.out.println("Adjacency List:");
        g.printGraph();

        System.out.println("\nBFS:");
        bfs(g, 0);

        System.out.println("DFS:");
        dfs(g, 0);

        System.out.println("Cycle Present: " + hasCycle(g));

        System.out.println("\nConnected Components:");
        int cc = connectedComponents(g);
        System.out.println("Total Components = " + cc);

        System.out.println("\nDijkstra from Node 0:");
        int[] dist = dijkstra(g, 0);

        for (int i = 0; i < dist.length; i++)
            System.out.println("0 -> " + i + " = " + dist[i]);

        System.out.println("\nTopological Sort Example:");

        int V = 6;

        List<List<Integer>> dag = new ArrayList<>();

        for (int i = 0; i < V; i++)
            dag.add(new ArrayList<>());

        dag.get(5).add(2);
        dag.get(5).add(0);
        dag.get(4).add(0);
        dag.get(4).add(1);
        dag.get(2).add(3);
        dag.get(3).add(1);

        System.out.println(topoSort(V, dag));

        System.out.println("\nDSU Example:");

        DSU dsu = new DSU(5);

        dsu.union(0, 1);
        dsu.union(1, 2);

        System.out.println("find(2) = " + dsu.find(2));
        System.out.println("find(4) = " + dsu.find(4));

        System.out.println("\nGraph revision completed successfully.");
    }
}