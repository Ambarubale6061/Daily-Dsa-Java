
// 462_SnakeAndLadder.java
import java.util.*;

public class SnakeAndLadder {
  public static int minMoves(int[] moves) {
    int n = moves.length;
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[n];
    q.add(0);
    visited[0] = true;
    int steps = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        int curr = q.poll();
        if (curr == n - 1)
          return steps;
        for (int dice = 1; dice <= 6 && curr + dice < n; dice++) {
          int next = moves[curr + dice] == -1 ? curr + dice : moves[curr + dice];
          if (!visited[next]) {
            visited[next] = true;
            q.add(next);
          }
        }
      }
      steps++;
    }
    return -1;
  }
}