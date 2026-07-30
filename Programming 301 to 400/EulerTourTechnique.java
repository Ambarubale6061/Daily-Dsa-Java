import java.util.*;

public class EulerTourTechnique {
    List<Integer>[] adj;
    int[] in, out;
    int timer;

    public EulerTourTechnique(int n, List<Integer>[] graph) {
        adj = graph;
        in = new int[n];
        out = new int[n];
        timer = 0;
        dfs(0, -1);
    }

    void dfs(int u, int p) {
        in[u] = timer++;
        for (int v : adj[u])
            if (v != p)
                dfs(v, u);
        out[u] = timer - 1;
    }
}