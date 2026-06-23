import java.util.Arrays;

public class AggressiveCows {
    public static int maxMinDistance(int[] stalls, int cows) {
        Arrays.sort(stalls);
        int l = 1, r = stalls[stalls.length - 1] - stalls[0];
        int ans = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (canPlace(stalls, cows, m)) {
                ans = m;
                l = m + 1;
            } else
                r = m - 1;
        }
        return ans;
    }

    private static boolean canPlace(int[] stalls, int cows, int dist) {
        int count = 1, last = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - last >= dist) {
                count++;
                last = stalls[i];
                if (count == cows)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] stalls = { 1, 2, 8, 4, 9 };
        int cows = 3;
        System.out.println(maxMinDistance(stalls, cows)); // 3
    }
}