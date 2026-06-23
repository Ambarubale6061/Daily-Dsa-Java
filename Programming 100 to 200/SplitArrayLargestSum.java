import java.util.Arrays;

public class SplitArrayLargestSum {
    public static int splitArray(int[] nums, int m) {
        int l = Arrays.stream(nums).max().getAsInt();
        int r = Arrays.stream(nums).sum();
        while (l < r) {
            int mid = l + (r - l) / 2;
            int sum = 0, count = 1;
            for (int num : nums) {
                if (sum + num > mid) {
                    count++;
                    sum = num;
                } else
                    sum += num;
            }
            if (count <= m)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums = { 7, 2, 5, 10, 8 };
        int m = 2;
        System.out.println(splitArray(nums, m)); // 18
    }
}