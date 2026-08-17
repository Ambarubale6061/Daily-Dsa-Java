
// 461_MinimumKnightMoves.java
import java.util.*;

public class MinimumKnightMoves {

  // Directions a knight can move
  private static final int[][] DIRS = {
      { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 },
      { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }
  };

  // Returns minimum knight moves from (0,0) to (x,y)
  public static int minMoves(int x, int y) {
    // Use symmetry (only first quadrant)
    x = Math.abs(x);
    y = Math.abs(y);

    Queue<int[]> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();

    queue.offer(new int[] { 0, 0, 0 }); // x, y, moves
    visited.add("0,0");

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int cx = curr[0];
      int cy = curr[1];
      int moves = curr[2];

      if (cx == x && cy == y) {
        return moves;
      }

      for (int[] d : DIRS) {
        int nx = cx + d[0];
        int ny = cy + d[1];

        // Limit search space using symmetry
        if (nx >= -2 && ny >= -2 && nx <= x + 2 && ny <= y + 2) {
          String key = nx + "," + ny;
          if (!visited.contains(key)) {
            visited.add(key);
            queue.offer(new int[] { nx, ny, moves + 1 });
          }
        }
      }
    }

    return -1; // Should never happen
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter target x coordinate: ");
    int x = sc.nextInt();

    System.out.print("Enter target y coordinate: ");
    int y = sc.nextInt();

    System.out.println("Minimum Knight Moves: " + minMoves(x, y));

    sc.close();
  }
}