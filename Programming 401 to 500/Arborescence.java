
// 478_Arborescence.java
import java.util.*;

public class Arborescence {

  /*
   * An Arborescence is a Directed Minimum Spanning Tree (DMST)
   * rooted at a specified node.
   *
   * This implementation delegates the computation to the
   * Chu–Liu/Edmonds algorithm implemented in DirectedMST.java.
   *
   * Edge Format:
   * edges[i][0] = source
   * edges[i][1] = destination
   * edges[i][2] = weight
   */

  public static int minimumArborescence(int n, int root, int[][] edges) {
    return DirectedMST.directedMST(n, root, edges);
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.print("Enter number of vertices: ");
    int n = sc.nextInt();

    System.out.print("Enter number of edges: ");
    int m = sc.nextInt();

    System.out.print("Enter root vertex: ");
    int root = sc.nextInt();

    int[][] edges = new int[m][3];

    System.out.println("Enter edges (from to weight):");

    for (int i = 0; i < m; i++) {
      edges[i][0] = sc.nextInt();
      edges[i][1] = sc.nextInt();
      edges[i][2] = sc.nextInt();
    }

    int cost = minimumArborescence(n, root, edges);

    if (cost == -1)
      System.out.println("Arborescence does not exist.");
    else
      System.out.println("Minimum Arborescence Cost = " + cost);

    sc.close();
  }
}