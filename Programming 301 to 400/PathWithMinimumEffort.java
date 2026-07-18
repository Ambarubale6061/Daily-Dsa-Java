import java.util.*;

public class PathWithMinimumEffort {
    public static int minimumEffortPath(int[][] heights) {
        int rows = heights.length, cols = heights[0].length;
        int[][] effort = new int[rows][cols];
        for (int[] r : effort)
            Arrays.fill(r, Integer.MAX_VALUE);
        effort[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // r,c,cost
        pq.add(new int[] { 0, 0, 0 });
        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0], c = cur[1], e = cur[2];
            if (e > effort[r][c])
                continue;
            if (r == rows - 1 && c == cols - 1)
                return e;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols)
                    continue;
                int newEffort = Math.max(e, Math.abs(heights[r][c] - heights[nr][nc]));
                if (newEffort < effort[nr][nc]) {
                    effort[nr][nc] = newEffort;
                    pq.add(new int[] { nr, nc, newEffort });
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] heights = { { 1, 2, 2 }, { 3, 8, 2 }, { 5, 3, 5 } };
        System.out.println(minimumEffortPath(heights)); // 2
    }
}