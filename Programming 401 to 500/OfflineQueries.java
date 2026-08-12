
// 440_OfflineQueries.java
import java.util.*;

public class OfflineQueries {
  // Example: answer queries offline with sorting and BIT
  public static int[] answerQueries(int[] arr, int[][] queries) {
    int n = arr.length;
    int[][] qs = new int[queries.length][];
    for (int i = 0; i < queries.length; i++)
      qs[i] = new int[] { queries[i][0], queries[i][1], i };
    Arrays.sort(qs, Comparator.comparingInt(a -> a[1]));
    int[] bit = new int[n + 1];
    Map<Integer, Integer> last = new HashMap<>();
    int[] res = new int[queries.length];
    int j = 0;
    for (int i = 0; i < n; i++) {
      if (last.containsKey(arr[i]))
        update(bit, last.get(arr[i]) + 1, -1);
      last.put(arr[i], i);
      update(bit, i + 1, 1);
      while (j < qs.length && qs[j][1] == i) {
        res[qs[j][2]] = query(bit, qs[j][1] + 1) - query(bit, qs[j][0]);
        j++;
      }
    }
    return res;
  }

  static void update(int[] bit, int idx, int delta) {
    for (; idx < bit.length; idx += idx & -idx)
      bit[idx] += delta;
  }

  static int query(int[] bit, int idx) {
    int sum = 0;
    for (; idx > 0; idx -= idx & -idx)
      sum += bit[idx];
    return sum;
  }
}