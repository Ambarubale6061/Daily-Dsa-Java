import java.util.*;

public class DijkstraAlgorithm {
    public static int[] dijkstra(List<List<int[]>> adj, int src) {
        int V = adj.size();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // node, weight
        pq.add(new int[] { src, 0 });
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            if (cur[1] > dist[u])
                continue;
            for (int[] nb : adj.get(u)) {
                int v = nb[0], w = nb[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[] { v, dist[v] });
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        adj.get(0).add(new int[] { 1, 4 });
        adj.get(0).add(new int[] { 2, 1 });
        adj.get(1).add(new int[] { 3, 1 });
        adj.get(2).add(new int[] { 1, 2 });
        adj.get(2).add(new int[] { 3, 5 });
        adj.get(3).add(new int[] { 4, 3 });
        int[] dist = dijkstra(adj, 0);
        System.out.println(Arrays.toString(dist)); // [0,3,1,4,7]
    }
}