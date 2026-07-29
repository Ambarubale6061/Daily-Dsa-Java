public class EggDropping {
    public static int superEggDrop(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1)
                    dp[i][j] = j;
                else if (j == 1)
                    dp[i][j] = 1;
                else {
                    int low = 1, high = j, ans = j;
                    while (low <= high) {
                        int mid = (low + high) / 2;
                        int breakCount = dp[i - 1][mid - 1];
                        int notBreakCount = dp[i][j - mid];
                        int worst = Math.max(breakCount, notBreakCount);
                        ans = Math.min(ans, worst + 1);
                        if (breakCount > notBreakCount)
                            high = mid - 1;
                        else
                            low = mid + 1;
                    }
                    dp[i][j] = ans;
                }
            }
        }
        return dp[k][n];
    }
}