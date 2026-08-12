
// 436_SmallToLarge.java
import java.util.*;

public class SmallToLarge {
  // Merge sets using small-to-large (union by size)
  public static <T> Set<T> mergeSets(Set<T> a, Set<T> b) {
    if (a.size() < b.size()) {
      Set<T> t = a;
      a = b;
      b = t;
    }
    a.addAll(b);
    return a;
  }
}