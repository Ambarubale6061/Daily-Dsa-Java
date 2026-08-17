
// 465_MazeShortestPath.java
import java.util.*;

public class MazeShortestPath {

  // Directions: Up, Down, Left, Right
  private static final int[][] DIRS = {
      { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
  };

  // Returns the shortest path length from (0,0) to (rows-1, cols-1)
  // 0 = Open Cell, 1 = Wall
  public static int shortestPath(int[][] maze) {
    int rows = maze.length;
    int cols = maze[0].length;

    // If start or destination is blocked
    if (maze[0][0] == 1 || maze[rows - 1][cols - 1] == 1) {
      return -1;
    }

    boolean[][] visited = new boolean[rows][cols];
    Queue<int[]> queue = new LinkedList<>();

    // {row, col, distance}
    queue.offer(new int[] { 0, 0, 0 });
    visited[0][0] = true;

    while (!queue.isEmpty()) {
      int[] current = queue.poll();

      int r = current[0];
      int c = current[1];
      int dist = current[2];

      // Destination reached
      if (r == rows - 1 && c == cols - 1) {
        return dist;
      }

      for (int[] dir : DIRS) {
        int nr = r + dir[0];
        int nc = c + dir[1];

        if (nr >= 0 && nr < rows &&
            nc >= 0 && nc < cols &&
            !visited[nr][nc] &&
            maze[nr][nc] == 0) {

          visited[nr][nc] = true;
          queue.offer(new int[] { nr, nc, dist + 1 });
        }
      }
    }

    // Destination cannot be reached
    return -1;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter number of rows: ");
    int rows = sc.nextInt();

    System.out.print("Enter number of columns: ");
    int cols = sc.nextInt();

    int[][] maze = new int[rows][cols];

    System.out.println("Enter the maze (0 = Open, 1 = Wall):");
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        maze[i][j] = sc.nextInt();
      }
    }

    int result = shortestPath(maze);

    if (result == -1) {
      System.out.println("No path exists.");
    } else {
      System.out.println("Shortest Path Length: " + result);
    }

    sc.close();
  }
}