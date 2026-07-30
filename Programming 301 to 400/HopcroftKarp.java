import java.util.*;

public class HopcroftKarp {
    static final int INF = Integer.MAX_VALUE;
    int[] pairU, pairV, dist;
    List<Integer>[] adj;
    int n, m;

    public HopcroftKarp(int n, int m, List<Integer>[] adj) {
        this.n = n;
        this.m = m;
        this.adj = adj;
        pairU = new int[n];
        pairV = new int[m];
        dist = new int[n];
    }

    public int maxMatching() {
        Arrays.fill(pairU, -1);
        Arrays.fill(pairV, -1);
        int result = 0;
        while (bfs()) {
            for (int u = 0; u < n; u++)
                if (pairU[u] == -1 && dfs(u))
                    result++;
        }
        return result;
    }

    boolean bfs() {
        Queue<Integer> q = new LinkedList<>();
        for (int u = 0; u < n; u++) {
            if (pairU[u] == -1) {
                dist[u] = 0;
                q.add(u);
            } else
                dist[u] = INF;
        }
        dist[n] = INF; // dummy
        while (!q.isEmpty()) {
            int u = q.poll();
            if (dist[u] < dist[n]) {
                for (int v : adj[u]) {
                    if (pairV[v] != -1 && dist[pairV[v]] == INF) {
                        dist[pairV[v]] = dist[u] + 1;
                        q.add(pairV[v]);
                    }
                }
            }
        }
        return dist[n] != INF;
    }

    boolean dfs(int u) {
        if (u != n) {
            for (int v : adj[u]) {
                if (pairV[v] == -1 || (dist[pairV[v]] == dist[u] + 1 && dfs(pairV[v]))) {
                    pairU[u] = v;
                    pairV[v] = u;
                    return true;
                }
            }
            dist[u] = INF;
            return false;
        }
        return true;
    }
}