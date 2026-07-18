
// Using Prim's algorithm to compute total weight
import java.util.*;

public class MinimumSpanningTree {
    public static int mstWeight(int V, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[] { e[1], e[2] });
            adj.get(e[1]).add(new int[] { e[0], e[2] });
        }
        boolean[] visited = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] { 0, 0 });
        int total = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            if (visited[u])
                continue;
            visited[u] = true;
            total += cur[1];
            for (int[] nb : adj.get(u)) {
                if (!visited[nb[0]])
                    pq.add(nb);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int V = 4;
        int[][] edges = { { 0, 1, 10 }, { 0, 2, 6 }, { 0, 3, 5 }, { 1, 3, 15 }, { 2, 3, 4 } };
        System.out.println(mstWeight(V, edges)); // 19
    }
}