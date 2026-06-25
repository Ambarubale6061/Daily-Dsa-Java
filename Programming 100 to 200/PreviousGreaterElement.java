import java.util.*;

public class PreviousGreaterElement {
    public static int[] previousGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                res[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 5, 2, 10 };
        System.out.println(Arrays.toString(previousGreaterElement(nums))); // [-1, -1, 5, -1]
    }
}