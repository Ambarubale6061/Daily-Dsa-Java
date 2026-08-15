
// 470_CycleLengthQueries.java
import java.util.*;

public class CycleLengthQueries {

  /*
   * Returns the cycle length formed by adding an edge between u and v
   * in an infinite binary tree.
   *
   * Cycle Length = Distance(u, v) + 1
   */
  public int getCycleLength(int u, int v) {
    int distance = 0;

    // Move both nodes upward until they meet (LCA)
    while (u != v) {
      if (u > v) {
        u /= 2;
      } else {
        v /= 2;
      }
      distance++;
    }

    // +1 for the added edge
    return distance + 1;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    CycleLengthQueries solver = new CycleLengthQueries();

    System.out.print("Enter number of queries: ");
    int q = sc.nextInt();

    while (q-- > 0) {
      System.out.print("Enter u and v: ");
      int u = sc.nextInt();
      int v = sc.nextInt();

      System.out.println("Cycle Length: " +
          solver.getCycleLength(u, v));
    }

    sc.close();
  }
}