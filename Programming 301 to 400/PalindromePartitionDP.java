public class PalindromePartitionDP {
    public static int minCut(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && (len <= 2 || isPal[i + 1][j - 1]))
                    isPal[i][j] = true;
            }
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (isPal[0][i]) {
                dp[i] = 0;
                continue;
            }
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (isPal[j + 1][i])
                    dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[n - 1];
    }
}