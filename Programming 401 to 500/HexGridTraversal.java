// 459_HexGridTraversal.java
public class HexGridTraversal {
  // Hex grid using cube coordinates
  public static int hexDistance(int x1, int y1, int z1, int x2, int y2, int z2) {
    return (Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2)) / 2;
  }
}