import java.util.HashSet;

public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums)
            set.add(n);
        int max = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) {
                int cur = n, streak = 1;
                while (set.contains(cur + 1)) {
                    cur++;
                    streak++;
                }
                max = Math.max(max, streak);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        System.out.println(longestConsecutive(nums)); // 4
    }
}