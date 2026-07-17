import java.util.*;

public class KruskalMST {
    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static int kruskalMST(int V, List<Edge> edges) {
        Collections.sort(edges);
        int[] parent = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;
        int mstWeight = 0;
        for (Edge e : edges) {
            int pu = find(parent, e.u), pv = find(parent, e.v);
            if (pu != pv) {
                parent[pu] = pv;
                mstWeight += e.w;
            }
        }
        return mstWeight;
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    public static void main(String[] args) {
        int V = 4;
        List<Edge> edges = Arrays.asList(new Edge(0, 1, 10), new Edge(0, 2, 6), new Edge(0, 3, 5), new Edge(1, 3, 15),
                new Edge(2, 3, 4));
        System.out.println(kruskalMST(V, edges)); // 19
    }
}