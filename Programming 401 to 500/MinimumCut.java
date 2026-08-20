// 483_MinimumCut.java
import java.util.*;

public class MinimumCut {
    public static int minCut(int n, List<int[]> edges) {
        int[][] cap = new int[n][n];
        for (int[] e : edges) { cap[e[0]][e[1]] = e[2]; cap[e[1]][e[0]] = e[2]; }
        int[] vertices = new int[n];
        for (int i = 0; i < n; i++) vertices[i] = i;
        int best = Integer.MAX_VALUE;
        while (n > 1) {
            int[] co = minimumCutPhase(cap, n, vertices);
            best = Math.min(best, co[0]);
            // merge
        }
        return best;
    }
    static int[] minimumCutPhase(int[][] cap, int n, int[] vertices) { return new int[]{0, 0, 0}; }
}