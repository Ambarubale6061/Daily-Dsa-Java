
// 463_OpenLock.java
import java.util.*;

public class OpenLock {
  public static int openLock(String[] deadends, String target) {
    Set<String> dead = new HashSet<>(Arrays.asList(deadends));
    if (dead.contains("0000"))
      return -1;
    Queue<String> q = new LinkedList<>();
    q.add("0000");
    Set<String> visited = new HashSet<>();
    visited.add("0000");
    int turns = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        String cur = q.poll();
        if (cur.equals(target))
          return turns;
        for (int j = 0; j < 4; j++) {
          char c = cur.charAt(j);
          String s1 = cur.substring(0, j) + (char) (c == '9' ? '0' : c + 1) + cur.substring(j + 1);
          String s2 = cur.substring(0, j) + (char) (c == '0' ? '9' : c - 1) + cur.substring(j + 1);
          if (!dead.contains(s1) && visited.add(s1))
            q.add(s1);
          if (!dead.contains(s2) && visited.add(s2))
            q.add(s2);
        }
      }
      turns++;
    }
    return -1;
  }
}