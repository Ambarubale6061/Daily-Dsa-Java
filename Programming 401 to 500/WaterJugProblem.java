
// 464_WaterJugProblem.java
import java.util.*;

public class WaterJugProblem {
  public static boolean canMeasureWater(int x, int y, int target) {
    if (target > x + y)
      return false;
    return target % gcd(x, y) == 0;
  }

  static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }
}