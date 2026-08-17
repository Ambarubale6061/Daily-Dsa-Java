
// 466_MultiSourceBFS.java
import java.util.*;

public class MultiSourceBFS {
  public static int[][] multiSourceBFS(int[][] grid, int[][] sources) {
    int n = grid.length, m = grid[0].length;
    int[][] dist = new int[n][m];
    for (int[] row : dist)
      Arrays.fill(row, -1);
    Queue<int[]> q = new LinkedList<>();
    for (int[] s : sources) {
      q.add(s);
      dist[s[0]][s[1]] = 0;
    }
    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      for (int[] d : dirs) {
        int nx = cur[0] + d[0], ny = cur[1] + d[1];
        if (nx >= 0 && ny >= 0 && nx < n && ny < m && grid[nx][ny] == 0 && dist[nx][ny] == -1) {
          dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
          q.add(new int[] { nx, ny });
        }
      }
    }
    return dist;
  }
}