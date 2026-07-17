import java.util.*;

public class BellmanFord {
    public static int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < V - 1; i++) {
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
                    dist[v] = dist[u] + w;
            }
        }
        // Check negative cycles (optional)
        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] edges = { { 0, 1, 4 }, { 0, 2, 1 }, { 1, 3, 1 }, { 2, 1, 2 }, { 2, 3, 5 }, { 3, 4, 3 } };
        int[] dist = bellmanFord(V, edges, 0);
        System.out.println(Arrays.toString(dist)); // [0,3,1,4,7]
    }
}