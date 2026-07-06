public class SubsetSum {
    public static boolean subsetSum(int[] nums, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = { 3, 34, 4, 12, 5, 2 };
        System.out.println(subsetSum(nums, 9)); // true
    }
}