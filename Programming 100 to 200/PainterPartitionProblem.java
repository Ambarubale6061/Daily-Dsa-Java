import java.util.Arrays;

public class PainterPartitionProblem {
    public static int minTime(int[] boards, int painters) {
        int l = Arrays.stream(boards).max().getAsInt();
        int r = Arrays.stream(boards).sum();
        int ans = r;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (canPaint(boards, painters, m)) {
                ans = m;
                r = m - 1;
            } else
                l = m + 1;
        }
        return ans;
    }

    private static boolean canPaint(int[] boards, int painters, int maxTime) {
        int cnt = 1, cur = 0;
        for (int b : boards) {
            if (cur + b > maxTime) {
                cnt++;
                cur = b;
                if (cnt > painters)
                    return false;
            } else
                cur += b;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] boards = { 10, 20, 30, 40 };
        int k = 2;
        System.out.println(minTime(boards, k)); // 60
    }
}