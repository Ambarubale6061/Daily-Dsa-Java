import java.util.*;

public class PrimMST {
    public static int primMST(List<List<int[]>> adj, int V) {
        boolean[] visited = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, weight]
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
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        adj.get(0).add(new int[] { 1, 10 });
        adj.get(1).add(new int[] { 0, 10 });
        adj.get(0).add(new int[] { 2, 6 });
        adj.get(2).add(new int[] { 0, 6 });
        adj.get(0).add(new int[] { 3, 5 });
        adj.get(3).add(new int[] { 0, 5 });
        adj.get(1).add(new int[] { 3, 15 });
        adj.get(3).add(new int[] { 1, 15 });
        adj.get(2).add(new int[] { 3, 4 });
        adj.get(3).add(new int[] { 2, 4 });
        System.out.println(primMST(adj, V)); // 19
    }
}