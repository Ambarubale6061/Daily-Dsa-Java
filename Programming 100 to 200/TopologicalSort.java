import java.util.*;

public class TopologicalSort {
    public static List<Integer> topologicalSort(List<List<Integer>> adj) {
        int V = adj.size();
        int[] indegree = new int[V];
        for (List<Integer> neighbors : adj)
            for (int v : neighbors)
                indegree[v]++;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++)
            if (indegree[i] == 0)
                q.add(i);
        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int v : adj.get(u))
                if (--indegree[v] == 0)
                    q.add(v);
        }
        return topo.size() == V ? topo : new ArrayList<>(); // empty if cycle
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);
        System.out.println(topologicalSort(adj)); // [4,5,0,2,3,1] or similar
    }
}