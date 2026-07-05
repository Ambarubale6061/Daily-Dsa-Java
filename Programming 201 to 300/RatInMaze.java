import java.util.*;

public class RatInMaze {
    public static List<String> findPath(int[][] maze) {
        List<String> paths = new ArrayList<>();
        if (maze[0][0] == 0)
            return paths;
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        dfs(maze, 0, 0, visited, "", paths);
        return paths;
    }

    private static void dfs(int[][] maze, int r, int c, boolean[][] visited, String path, List<String> paths) {
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            paths.add(path);
            return;
        }
        visited[r][c] = true;
        int[][] dirs = { { 1, 0, 'D' }, { 0, -1, 'L' }, { 0, 1, 'R' }, { -1, 0, 'U' } };
        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nc >= 0 && nr < maze.length && nc < maze[0].length && maze[nr][nc] == 1
                    && !visited[nr][nc]) {
                dfs(maze, nr, nc, visited, path + (char) d[2], paths);
            }
        }
        visited[r][c] = false;
    }

    public static void main(String[] args) {
        int[][] maze = { { 1, 0, 0 }, { 1, 1, 0 }, { 0, 1, 1 } };
        System.out.println(findPath(maze));
    }
}