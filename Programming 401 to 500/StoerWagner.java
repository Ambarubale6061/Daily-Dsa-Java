// 486_StoerWagner.java
import java.util.*;

public class StoerWagner {
    public static int globalMinCut(int n, int[][] graph) {
        int[] vertices = new int[n];
        for (int i = 0; i < n; i++) vertices[i] = i;
        int best = Integer.MAX_VALUE;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) System.arraycopy(graph[i], 0, g[i], 0, n);
        while (n > 1) {
            int[] w = new int[n];
            boolean[] added = new boolean[n];
            int prev = -1;
            for (int i = 0; i < n; i++) {
                int sel = -1;
                for (int j = 0; j < n; j++) if (!added[j] && (sel == -1 || w[j] > w[sel])) sel = j;
                if (i == n - 1) {
                    best = Math.min(best, w[sel]);
                    // merge sel and prev
                    for (int j = 0; j < n; j++) {
                        g[prev][j] += g[sel][j];
                        g[j][prev] = g[prev][j];
                    }
                    g[sel] = g[n - 1];
                    for (int j = 0; j < n; j++) g[j][sel] = g[j][n - 1];
                    n--;
                } else {
                    added[sel] = true;
                    for (int j = 0; j < n; j++) w[j] += g[sel][j];
                    prev = sel;
                }
            }
        }
        return best;
    }
}