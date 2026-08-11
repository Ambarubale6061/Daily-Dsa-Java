
// 445_GraphCompression.java
import java.util.*;

public class GraphCompression {
  // Coordinate compression for graph nodes (e.g., large IDs -> 0..n-1)
  public static Map<Integer, Integer> compress(int[] nodes) {
    TreeSet<Integer> set = new TreeSet<>();
    for (int x : nodes)
      set.add(x);
    Map<Integer, Integer> map = new HashMap<>();
    int id = 0;
    for (int x : set)
      map.put(x, id++);
    return map;
  }
}