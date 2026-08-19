// 498_GridComponents.java
import java.util.*;

public class GridComponents {
    public static int countComponents(int[][] grid) {
        int n = grid.length, m = grid[0].length, count = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }
    static void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) return;
        visited[i][j] = true;
        dfs(grid, i + 1, j, visited); dfs(grid, i - 1, j, visited);
        dfs(grid, i, j + 1, visited); dfs(grid, i, j - 1, visited);
    }
}