public class TwoSingleNumbers {
    public static int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int n : nums)
            xor ^= n;
        int mask = xor & (-xor);
        int a = 0, b = 0;
        for (int n : nums) {
            if ((n & mask) == 0)
                a ^= n;
            else
                b ^= n;
        }
        return new int[] { a, b };
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 1, 3, 2, 5 };
        System.out.println(java.util.Arrays.toString(singleNumber(nums))); // [3,5]
    }
}