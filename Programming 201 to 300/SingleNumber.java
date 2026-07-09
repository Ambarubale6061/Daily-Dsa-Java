public class SingleNumber {
    public static int singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums)
            xor ^= num;
        return xor;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 1, 2, 1, 2 };
        System.out.println(singleNumber(nums)); // 4
    }
}