public class UnionFindCycle {
    public static boolean hasCycle(int V, int[][] edges) {
        int[] parent = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            int pu = find(parent, u), pv = find(parent, v);
            if (pu == pv)
                return true;
            parent[pu] = pv;
        }
        return false;
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    public static void main(String[] args) {
        int V = 3;
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
        System.out.println(hasCycle(V, edges)); // true
    }
}