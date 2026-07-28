public class UnboundedKnapsack {
    public static int unboundedKnapsack(int W, int[] wt, int[] val) {
        int[] dp = new int[W + 1];
        for (int i = 0; i <= W; i++) {
            for (int j = 0; j < wt.length; j++) {
                if (wt[j] <= i)
                    dp[i] = Math.max(dp[i], val[j] + dp[i - wt[j]]);
            }
        }
        return dp[W];
    }
}