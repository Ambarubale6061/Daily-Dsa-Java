import java.util.Arrays;

public class LongestBitonicSubsequence {
    public static int longestBitonic(int[] nums) {
        int n = nums.length;
        int[] lis = new int[n], lds = new int[n];
        Arrays.fill(lis, 1);
        Arrays.fill(lds, 1);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    lis[i] = Math.max(lis[i], lis[j] + 1);
        for (int i = n - 1; i >= 0; i--)
            for (int j = n - 1; j > i; j--)
                if (nums[i] > nums[j])
                    lds[i] = Math.max(lds[i], lds[j] + 1);
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, lis[i] + lds[i] - 1);
        return max;
    }
}