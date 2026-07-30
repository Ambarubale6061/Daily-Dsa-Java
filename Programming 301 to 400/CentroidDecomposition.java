import java.util.*;

public class CentroidDecomposition {
    List<Integer>[] adj;
    int[] sz;
    boolean[] removed;

    public CentroidDecomposition(int n, List<Integer>[] graph) {
        adj = graph;
        sz = new int[n];
        removed = new boolean[n];
    }

    public void decompose(int u) {
        dfsSize(u, -1);
        int centroid = findCentroid(u, -1, sz[u] / 2);
        // do something with centroid
        removed[centroid] = true;
        for (int v : adj[centroid])
            if (!removed[v])
                decompose(v);
    }

    int dfsSize(int u, int p) {
        sz[u] = 1;
        for (int v : adj[u])
            if (v != p && !removed[v])
                sz[u] += dfsSize(v, u);
        return sz[u];
    }

    int findCentroid(int u, int p, int half) {
        for (int v : adj[u])
            if (v != p && !removed[v] && sz[v] > half)
                return findCentroid(v, u, half);
        return u;
    }
}