public class MinimumSubsetDifference {
    public static int minDifference(int[] nums) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;
        for (int n : nums)
            for (int i = dp.length - 1; i >= n; i--)
                dp[i] |= dp[i - n];
        int min = sum;
        for (int i = 0; i <= sum / 2; i++)
            if (dp[i])
                min = Math.min(min, sum - 2 * i);
        return min;
    }
}