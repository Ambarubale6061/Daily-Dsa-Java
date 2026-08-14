
// 458_GridShortestPath.java
import java.util.*;

public class GridShortestPath {
  public static int shortestPath(int[][] grid, int sx, int sy, int ex, int ey) {
    int n = grid.length, m = grid[0].length;
    int[][] dist = new int[n][m];
    for (int[] row : dist)
      Arrays.fill(row, Integer.MAX_VALUE);
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] { sx, sy });
    dist[sx][sy] = 0;
    int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      if (cur[0] == ex && cur[1] == ey)
        return dist[ex][ey];
      for (int[] d : dirs) {
        int nx = cur[0] + d[0], ny = cur[1] + d[1];
        if (nx >= 0 && ny >= 0 && nx < n && ny < m && grid[nx][ny] == 0 && dist[nx][ny] == Integer.MAX_VALUE) {
          dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
          q.add(new int[] { nx, ny });
        }
      }
    }
    return -1;
  }
}