import java.util.*;

public class HeavyLightDecomposition {
    // Simplified HLD for path sum queries
    List<Integer>[] adj;
    int[] parent, depth, heavy, head, pos;
    int curPos;
    int[] tree; // segment tree array

    public HeavyLightDecomposition(int n, List<Integer>[] graph) {
        adj = graph;
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        Arrays.fill(heavy, -1);
        dfs(0);
        curPos = 0;
        decompose(0, 0);
    }

    int dfs(int u) {
        int size = 1, maxSubtree = 0;
        for (int v : adj[u]) {
            if (v != parent[u]) {
                parent[v] = u;
                depth[v] = depth[u] + 1;
                int subSize = dfs(v);
                if (subSize > maxSubtree) {
                    maxSubtree = subSize;
                    heavy[u] = v;
                }
                size += subSize;
            }
        }
        return size;
    }

    void decompose(int u, int h) {
        head[u] = h;
        pos[u] = curPos++;
        if (heavy[u] != -1)
            decompose(heavy[u], h);
        for (int v : adj[u]) {
            if (v != parent[u] && v != heavy[u])
                decompose(v, v);
        }
    }
    // use segment tree on pos array to update/query values
}