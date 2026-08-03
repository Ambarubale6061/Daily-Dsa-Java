
// 406_AStarSearch.java
import java.util.*;

public class AStarSearch {
  // A* on grid with Manhattan heuristic
  static class Node {
    int x, y;
    Node parent;
    int g, f;

    Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int heuristic(Node a, Node b) {
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
  }

  public static List<Node> astar(int[][] grid, int sx, int sy, int ex, int ey) {
    int n = grid.length, m = grid[0].length;
    Node start = new Node(sx, sy), target = new Node(ex, ey);
    PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(a -> a.f));
    boolean[][] closed = new boolean[n][m];
    Node[][] allNodes = new Node[n][m];
    allNodes[sx][sy] = start;
    start.g = 0;
    start.f = heuristic(start, target);
    open.add(start);
    int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    while (!open.isEmpty()) {
      Node cur = open.poll();
      if (cur.x == ex && cur.y == ey) {
        List<Node> path = new ArrayList<>();
        while (cur != null) {
          path.add(cur);
          cur = cur.parent;
        }
        Collections.reverse(path);
        return path;
      }
      closed[cur.x][cur.y] = true;
      for (int[] d : dirs) {
        int nx = cur.x + d[0], ny = cur.y + d[1];
        if (nx < 0 || nx >= n || ny < 0 || ny >= m || grid[nx][ny] == 1 || closed[nx][ny])
          continue;
        int tentative = cur.g + 1;
        Node neighbor = allNodes[nx][ny];
        if (neighbor == null) {
          neighbor = new Node(nx, ny);
          allNodes[nx][ny] = neighbor;
        } else if (tentative >= neighbor.g)
          continue;
        neighbor.parent = cur;
        neighbor.g = tentative;
        neighbor.f = neighbor.g + heuristic(neighbor, target);
        open.add(neighbor);
      }
    }
    return null;
  }

  public static void main(String[] args) {
    int[][] grid = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
    List<Node> path = astar(grid, 0, 0, 2, 2);
    if (path != null)
      for (Node p : path)
        System.out.println(p.x + "," + p.y);
  }
}