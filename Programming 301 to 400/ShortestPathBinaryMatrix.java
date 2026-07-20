import java.util.*;

public class ShortestPathBinaryMatrix {
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { 0, 0 });
        int dist = 1;
        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1];
                if (r == n - 1 && c == n - 1)
                    return dist;
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 0) {
                        grid[nr][nc] = 1;
                        q.add(new int[] { nr, nc });
                    }
                }
            }
            dist++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = { { 0, 1 }, { 1, 0 } };
        System.out.println(shortestPathBinaryMatrix(grid)); // 2
    }
}